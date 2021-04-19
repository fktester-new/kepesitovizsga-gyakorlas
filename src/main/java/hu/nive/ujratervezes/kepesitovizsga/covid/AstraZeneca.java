package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class AstraZeneca extends Vaccine{

    public AstraZeneca(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> temp = super.getVaccinationList();
        Set<Person> chronicals = addChronicals(temp);
        Set<Person> retired = addRetired(chronicals, temp);
        return addOtherNonPregnant(retired, temp);
    }

    private List<Person> addOtherNonPregnant(Set<Person> retired, List<Person> temp) {
        for (Person person : temp) {
            if (person.getPregnant() == Pregnancy.NEM){
                retired.add(person);
            }
        }
        return new ArrayList<>(retired);
    }

    private Set<Person> addRetired(Set<Person> chronicals, List<Person> temp) {
        for (Person person : temp) {
            if (person.getAge() > 65){
                chronicals.add(person);
            }
        }
        return chronicals;
    }

    private Set<Person> addChronicals(List<Person> temp) {
        Set<Person> chronicals = new LinkedHashSet<>();
        for (Person person : temp) {
           if (person.getChronic() == ChronicDisease.IGEN && person.getPregnant() == Pregnancy.NEM){
               chronicals.add(person);
           }
        }
        return chronicals;
    }
}
