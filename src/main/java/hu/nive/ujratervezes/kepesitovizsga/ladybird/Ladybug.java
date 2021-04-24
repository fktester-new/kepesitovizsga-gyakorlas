package hu.nive.ujratervezes.kepesitovizsga.ladybird;

public class Ladybug {

    private String hungarianName;
    private String latinName;
    private String genus;
    private int numberOfPoints;

    public Ladybug(String hungarianName, String latinName, String genus, int numberOfPoints) {
        this.hungarianName = hungarianName;
        this.latinName = latinName;
        this.genus = genus;
        this.numberOfPoints = numberOfPoints;
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public String getLatinName() {
        return latinName;
    }

    public String getGenus() {
        return genus;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }
}
