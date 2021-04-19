package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SinoPharm extends Vaccine{

    public SinoPharm(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Person> getVaccinationList() {
        List<Person> temp = super.getVaccinationList();
        Set<Person> youngs = addNonPregnantNonChronicYoungs(temp);
        return addNonChronicRetired(youngs, temp);
    }

    private List<Person> addNonChronicRetired(Set<Person> youngs, List<Person> temp) {
        for (Person person : temp) {
            if (person.getChronic() == ChronicDisease.NEM && person.getAge() > 65 && person.getPregnant() == Pregnancy.NEM){
                youngs.add(person);
            }
        }
        return new ArrayList<>(youngs);
    }

    private Set<Person> addNonPregnantNonChronicYoungs(List<Person> temp) {
        Set<Person> youngs = new LinkedHashSet<>();
        for (Person person : temp){
            if (person.getChronic() == ChronicDisease.NEM && person.getPregnant() == Pregnancy.NEM && person.getAge() <= 65){
                youngs.add(person);
            }
        }
        return youngs;
    }
}
