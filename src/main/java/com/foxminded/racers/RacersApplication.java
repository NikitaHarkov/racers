package com.foxminded.racers;

import com.foxminded.racers.model.Racer;
import com.foxminded.racers.services.RacersResults;
import com.foxminded.racers.services.ResultTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RacersApplication {
    public static void main(String[] args) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
            File starts = new File(classLoader.getResource("start.log").getFile());
            File ends = new File(classLoader.getResource("end.log").getFile());

            RacersResults list = new RacersResults();
            try {
                List<Racer> racers = list.createListOfRacers(abbreviations, starts, ends);
                ResultTable resultTable = new ResultTable();
                resultTable.formatRacersResults(racers).forEach(System.out::println);
            } catch (IOException ex) {
                throw new IllegalArgumentException("Error with reading files");
            }
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Please, check for files");
        }
    }
}
