package com.foxminded.racers;

import com.foxminded.racers.model.Racer;
import com.foxminded.racers.services.ListOfRacers;
import com.foxminded.racers.services.ResultTable;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RacersApplication {
    public static void main(String[] args) throws IOException {
        File abbreviations = new File("src/main/resources/abbreviations.txt");
        File starts = new File("src/main/resources/start.log");
        File ends = new File("src/main/resources/end.log");

        ListOfRacers list = new ListOfRacers();
        List<Racer> racers = list.createListOfRacers(abbreviations, starts, ends);
        ResultTable resultTable = new ResultTable();
        List<String> result = resultTable.formatListOfRacers(racers);
        result.forEach(System.out::println);
    }
}
