package hu.nive.ujratervezes.kepesitovizsga.tree;

public class CherryTree extends Tree{

    public CherryTree(int leaves) {
        super(leaves);
        fruit = Fruit.CHERRY;
    }

    @Override
    public int growLeaves(int numberOfSunnyDays) {
        leaves += numberOfSunnyDays * 20;
        return leaves;
    }

    @Override
    public void ripenFruit(int numberOfSunnyDays) {
        weightOfFruit = growLeaves(numberOfSunnyDays) / 30;
    }

}
