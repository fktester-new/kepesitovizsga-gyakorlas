package hu.nive.ujratervezes.kepesitovizsga.uppercase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UpperCaseLetters {

    public int getNumberOfUpperCase(String filename){
        List<String> temp = loadCharacters(filename);
        char[] result = makeCharArray(temp);
        int counter = 0;
        for (char c : result) {
            if (c >= 'A' && c <= 'Z'){
                counter++;
            }
        }
        return counter;
    }


    private char[] makeCharArray(List<String> temp){
        StringBuilder sb = new StringBuilder();
        for (String s: temp) {
            sb.append(s);
        }
        return sb.toString().toCharArray();

    }


    private List<String> loadCharacters(String fileName){
        Path path = Path.of("src", "main", "resources", fileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file.", e);
        }
    }
}
