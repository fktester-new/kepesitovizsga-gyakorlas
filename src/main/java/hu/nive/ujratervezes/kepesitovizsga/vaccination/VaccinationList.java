package hu.nive.ujratervezes.kepesitovizsga.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VaccinationList {

    private MetaData metaData;
    private Map<LocalTime, Person> vaccinations = new TreeMap<>();

    public void readFromFile(BufferedReader br) {
        try {
            createMetaData(br);
            readUnnecessaryLines(br);
            loadMap(br);

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file.", ioe);
        }
    }

    private void readUnnecessaryLines(BufferedReader br) throws IOException {
        br.readLine();
        br.readLine();
    }

    private void loadMap(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String t = line.substring(0, line.indexOf(";"));
            LocalTime time = LocalTime.parse(t, DateTimeFormatter.ofPattern("H:mm"));
            String data = line.substring(line.indexOf(";") + 1);
            Person person = Person.parse(data);
            vaccinations.put(time, person);
        }
    }

    private void createMetaData(BufferedReader br) throws IOException {
        String firstLine = br.readLine();
        String secondLine = br.readLine();
        String postalCode = firstLine.substring(firstLine.indexOf(",") - 4, firstLine.indexOf(","));
        String town = firstLine.substring(firstLine.indexOf(",") + 2).replace(" településre", "");

        String date = secondLine.substring(secondLine.indexOf(":") + 2);
        LocalDate day = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        metaData = new MetaData(postalCode, town, day);
    }

    public List<Person> getPersonsMoreThanHundredYearsOld() {
        Collection<Person> persons = vaccinations.values();
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getAge() > 100) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Person> getAfternoonPersons() {
        List<Person> result = new ArrayList<>();
        for (Map.Entry<LocalTime, Person> entries : vaccinations.entrySet()) {
            if (entries.getKey().isAfter(LocalTime.of(12, 0, 0))) {
                result.add(entries.getValue());
            }
        }
        return result;
    }

    public void validateTaj() {
        Collection<Person> persons = vaccinations.values();
        StringBuilder wrongTaj = new StringBuilder();
        for (Person p : persons) {
            if (!Person.validateTaj(p.getTaj())) {
                wrongTaj.append(p.getTaj()).append(", ");
            }
        }
        if (!wrongTaj.isEmpty()){
            String message = wrongTaj.substring(0,wrongTaj.lastIndexOf(",") );
            throw new IllegalArgumentException(message);
        }

    }

    public String inviteExactPerson(LocalTime time) {
        String name = findPersonInvitedAt(time);
        return readTemplate(name);
    }

    public Town getTown(){
        return metaData.getTown();
    }

    public LocalDate getDateOfVaccination(){
        return metaData.getDate();
    }

    public Map<VaccinationType, Integer> getVaccinationStatistics(){
        Map<VaccinationType, Integer> statistics = new HashMap<>();
        Collection<Person> persons = vaccinations.values();
        for (Person p : persons) {
            VaccinationType key = p.getVaccinationType();
            if(!statistics.containsKey(key)){
                statistics.put(key, 1);
            } else {
                statistics.put(key, statistics.get(key) + 1);
            }
        }
        return statistics;
    }

    private String readTemplate(String name) {
        Path file = Path.of("greeting.txt");
        try{
            String greeting = Files.readString(file);
            return greeting.replace("{nev}", name);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file.", e);
        }
    }

    private String findPersonInvitedAt(LocalTime time){
        for (Map.Entry<LocalTime, Person> entries : vaccinations.entrySet()){
            if (entries.getKey().equals(time)){
                return entries.getValue().getName();
            }
        }
        throw new IllegalArgumentException("There is nobody invited to this time:" + time);
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Map<LocalTime, Person> getVaccinations() {
        return vaccinations;
    }
}
