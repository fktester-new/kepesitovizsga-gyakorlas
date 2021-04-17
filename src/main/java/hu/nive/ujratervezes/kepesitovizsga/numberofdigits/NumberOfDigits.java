package hu.nive.ujratervezes.kepesitovizsga.numberofdigits;

public class NumberOfDigits {

    public int getNumberOfDigits(int n){
        int numberOfDigits = 0;
        for (int i = 1; i <= n; i++){
            numberOfDigits += Integer.toString(i).length();
        }
        return numberOfDigits;
    }
}
