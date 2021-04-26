package hu.nive.ujratervezes.kepesitovizsga.vaccination;

public class Town {

    private String zip;
    private String townName;

    public Town(String zip, String townName) {
        this.zip = zip;
        this.townName = townName;
    }

    public String getZip() {
        return zip;
    }

    public String getTownName() {
        return townName;
    }

    @Override
    public String toString() {
        return zip + ", " + townName;
    }
}
