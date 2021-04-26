package hu.nive.ujratervezes.kepesitovizsga.vaccination;

import java.time.LocalDate;

public class MetaData {

    private Town town;
    private LocalDate date;

    public MetaData(String postalCode, String townName, LocalDate date) {
        this.town = new Town(postalCode, townName);
        this.date = date;
    }

    public Town getTown() {
        return town;
    }

    public String getTownName(){
        return town.getTownName();
    }

    public String getPostalCode(){
        return town.getZip();
    }

    public LocalDate getDate() {
        return date;
    }
}
