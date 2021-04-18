package hu.nive.ujratervezes.kepesitovizsga.dogs;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DogManager {

    private List<Dog> dogs = new ArrayList<>();

    public DogManager() {
        this.readDogsFromFile("dogs.csv");
    }

    public String getCountryByExactDogSpecies(String name){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("The name is empty or null.");
        }
        if (dogs == null){
            throw new IllegalArgumentException("There is not any dog in the list.");
        }
        for (Dog dog : dogs) {
            if (dog.getName().equalsIgnoreCase(name)){
                return dog.getCountry();
            }
        }
        throw new IllegalArgumentException("No such dog name found.");
    }

    public List<Dog> getDogsInAlphabeticalOrderByName(){
        dogs.sort(new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return dogs;
    }

    public Map<String, Integer> getDogStatistics() {
        Map<String, Integer> result = new HashMap<>();
        for (Dog dog : dogs) {
            if (!result.containsKey(dog.getCountry())) {
                result.put(dog.getCountry(), 1);
            } else {
                result.put(dog.getCountry(), result.get(dog.getCountry()) + 1);
            }
        }
        return result;
    }

    private void readDogsFromFile(String fileName){
        Path file = Path.of("src", "main", "resources", fileName);
        try(BufferedReader br = Files.newBufferedReader(file)){
            String line;
            // A fejlecsor eldobasa
            br.readLine();
            while ((line = br.readLine()) != null){
                Dog dog = parseLine(line);
                dogs.add(dog);
            }
        }
        catch (IOException e) {
            throw new IllegalStateException("Cannot read file!", e);
        }
    }

    private Dog parseLine(String line){
        String[] temp = line.split(";");
        return new Dog(temp[1], temp[4], temp[5]);
    }


}



