package hu.nive.ujratervezes.kepesitovizsga.tree;

public abstract class Tree {

    protected int leaves;
    protected Fruit fruit;
    protected int weightOfFruit;

    protected Tree(int leaves) {
        this.leaves = leaves;
    }

    public abstract int growLeaves(int numberOfSunnyDays);
    public abstract void ripenFruit(int numberOfSunnyDays);
    public int hostBirdNest(){
        return leaves / 200;
    }

    public int getLeaves() {
        return leaves;
    }

    public Fruit getFruit() {
        return fruit;
    }
}
