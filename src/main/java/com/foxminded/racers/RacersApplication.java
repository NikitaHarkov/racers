package com.foxminded.racers;

import com.foxminded.racers.model.Racer;
import com.foxminded.racers.services.RacersResults;
import com.foxminded.racers.services.ResultTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RacersApplication {
    public static void main(String[] args) {
        try {
            File abbreviations = getFile("abbreviations.txt");
            File starts = getFile("start.log");
            File ends = getFile("end2.log");

            RacersResults list = new RacersResults();
            try {
                List<Racer> racers = list.createListOfRacers(abbreviations, starts, ends);
                ResultTable resultTable = new ResultTable();
                resultTable.formatRacersResults(racers).forEach(System.out::println);
            } catch (IOException ex) {
                System.out.println("Error with reading files:\n" + ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex + "\n Please, check for files!");
        }
    }

    private static File getFile(String file) throws FileNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(file);
        if (url == null) {
            throw new FileNotFoundException();
        }
        return new File(url.getFile());
    }
}
