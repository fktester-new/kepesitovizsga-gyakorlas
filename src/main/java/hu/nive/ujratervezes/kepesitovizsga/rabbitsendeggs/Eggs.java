package hu.nive.ujratervezes.kepesitovizsga.rabbitsendeggs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Eggs {

    private List<Rabbit> rabbits = new ArrayList<>();

    private List<String> readLinesFromFile(Path path) {
        try {
            List<String> result = Files.readAllLines(path);
            return result;
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file.", e);
        }
    }

    private void loadRabbits(List<String> data) {
        for (String line : data) {
            String[] temp = line.split(";");
            Rabbit rabbit = new Rabbit(temp[0], Integer.parseInt(temp[1]));
            rabbits.add(rabbit);
        }
    }

    public Rabbit getRabbitWithMaxEggs() {
        Path path = Path.of("src", "main", "resources", "eggs.csv");
        List<String> result = readLinesFromFile(path);
        loadRabbits(result);
        int maxEggs = 0;
        Rabbit rabbitWithMaxEggs = null;
        if (rabbits != null && !rabbits.isEmpty()) {
            for (Rabbit rabbit : rabbits) {
                if (rabbit.getEggs() > maxEggs) {
                    maxEggs = rabbit.getEggs();
                    rabbitWithMaxEggs = rabbit;
                }
            }
        }
        return rabbitWithMaxEggs;
    }
}
