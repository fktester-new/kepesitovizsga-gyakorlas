package hu.nive.ujratervezes.kepesitovizsga.tree;

public class WalnutTree extends Tree{

    public WalnutTree(int leaves) {
        super(leaves);
        fruit = Fruit.WALNUT;
    }

    @Override
    public int growLeaves(int numberOfSunnyDays) {
        leaves += numberOfSunnyDays * 30;
        return leaves;
    }

    @Override
    public void ripenFruit(int numberOfSunnyDays) {
        weightOfFruit = growLeaves(numberOfSunnyDays) / 10;
    }

}
