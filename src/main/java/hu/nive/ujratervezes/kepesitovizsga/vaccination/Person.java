package hu.nive.ujratervezes.kepesitovizsga.vaccination;

public class Person {

    private String name;
    String zip;
    private int age;
    private String email;
    private String taj;
    private VaccinationType vaccinationType;

    public Person(String name, String zip, int age, String email, String taj, VaccinationType vaccinationType) {
        this.name = name;
        this.zip = zip;
        this.age = age;
        this.email = email;
        this.taj = taj;
        this.vaccinationType = vaccinationType;
    }

    public static Person parse(String data) {
        String[] parts = data.split(";");

        return new Person(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                parts[3],
                parts[4],
                parts.length > 5 ? VaccinationType.valueOf(parts[5]) : VaccinationType.NONE);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTaj() {
        return taj;
    }

    public String getZip() {        return zip;
    }

    public VaccinationType getVaccinationType() {
        return vaccinationType;
    }
}
