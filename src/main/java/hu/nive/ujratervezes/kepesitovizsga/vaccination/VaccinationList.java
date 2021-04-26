package hu.nive.ujratervezes.kepesitovizsga.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
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

    public List<Person> getPersonsMoreThanHundredYearsOld(){
        Collection<Person> persons = vaccinations.values();
        List<Person> result = new ArrayList<>();
        for (Person p : persons) {
            if (p.getAge() > 100){
                result.add(p);
            }
        }
       return result;
    }

    public List<Person> getAfternoonPersons(){
        List<Person> result = new ArrayList<>();
        for (Map.Entry<LocalTime, Person> entries : vaccinations.entrySet()){
            if (entries.getKey().isAfter(LocalTime.of(12, 0, 0))){
                result.add(entries.getValue());
            }
        }
        return result;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Map<LocalTime, Person> getVaccinations() {
        return vaccinations;
    }
}
