package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class Pfizer extends Vaccine{


    public Pfizer(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> temp = super.getVaccinationList();
        List<Person> pregnants = addPregnants(temp);
        List<Person> retired = addRetired(pregnants, temp);
        return addOthers(retired, temp);
    }

    private List<Person> addOthers(List<Person> retired, List<Person> temp) {
        for (Person person : temp) {
            if (person.getAge() <= 65 && person.getPregnant() != Pregnancy.IGEN){
                retired.add(person);
            }
        }
        return retired;
    }

    private List<Person> addRetired(List<Person> pregnants, List<Person> temp) {
        for (Person person : temp) {
            if (person.getAge() > 65){
                pregnants.add(person);
            }
        }
        return pregnants;
    }

    private List<Person> addPregnants(List<Person> people){
        List<Person> pregnants = new ArrayList<>();
        for (Person person : people) {
            if (person.getPregnant() == Pregnancy.IGEN){
                pregnants.add(person);
            }
        }
        return pregnants;
    }
}
