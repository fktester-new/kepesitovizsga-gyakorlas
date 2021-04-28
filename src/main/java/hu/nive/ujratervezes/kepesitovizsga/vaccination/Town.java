package hu.nive.ujratervezes.kepesitovizsga.vaccination;

public class Town {

    private String postalCode;
    private String townName;

    public Town(String postalCode, String townName) {
        this.postalCode = postalCode;
        this.townName = townName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTownName() {
        return townName;
    }

    @Override
    public String toString() {
        return postalCode + ", " + townName;
    }
}
