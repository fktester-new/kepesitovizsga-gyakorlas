package hu.nive.ujratervezes.kepesitovizsga.rabbitsendeggs;

public class Rabbit {

    private String name;
    private int eggs;

    public Rabbit(String name, int eggs) {
        this.name = name;
        this.eggs = eggs;
    }

    public String getName() {
        return name;
    }

    public int getEggs() {
        return eggs;
    }
}
