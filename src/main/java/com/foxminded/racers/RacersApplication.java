package com.foxminded.racers;

import com.foxminded.racers.model.Racer;
import com.foxminded.racers.services.RacersResults;
import com.foxminded.racers.services.ResultTable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RacersApplication {
    public static void main(String[] args) {
        File abbreviations = getFile("abbreviations.txt");
        File starts = getFile("start.log");
        File ends = getFile("end.log");

        RacersResults list = new RacersResults();
        try {
            List<Racer> racers = list.createListOfRacers(abbreviations, starts, ends);
            ResultTable resultTable = new ResultTable();
            resultTable.formatRacersResults(racers);
        } catch (IOException ex) {
            System.out.println("Error with reading files \n" + ex);
        }
    }

    public static File getFile(String file) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(file);
        if (url == null) {
            throw new IllegalArgumentException("File doesn't exist");
        }
        return new File(url.getFile());
    }
}
