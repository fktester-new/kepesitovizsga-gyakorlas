package hu.nive.ujratervezes.kepesitovizsga.sumofdigits;

import java.util.Random;

public class SumOfDigits {

    Random random = new Random();

    public int getSumOfDigits(Random random) {
        int number = random.nextInt();
        number = Math.abs(number);

        while (number > 9) {
            int sumOfDigits = 0;
            while (number > 0) {
                sumOfDigits += number % 10;
                number /= 10;
            }
            number = sumOfDigits;
        }
        return number;
    }
}
