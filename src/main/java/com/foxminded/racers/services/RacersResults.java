package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RacersResults {
    public List<Racer> createListOfRacers(File abbreviations, File start, File end) throws IOException {
        FileParser fileParser = new FileParser();
        List<Racer> racers = fileParser.parseRacersFromFile(abbreviations);

        LapDuration lapDuration = new LapDuration();
        Map<String, Long> lapTime = lapDuration.calculateLapTime(
                fileParser.getStartEventTime(start),
                fileParser.getEndEventTime(end));

        addLapTimeToRacers(racers, lapTime);
        sortByLapTime(racers);
        return racers;
    }

    private void addLapTimeToRacers(List<Racer> racers, Map<String, Long> lapTime) {
        racers.forEach(racer -> racer.setLapTime(
                lapTime.get(racer.getAbbreviation())
        ));
    }

    private void sortByLapTime(List<Racer> racers) {
        racers.sort(Comparator.comparingLong(Racer::getLapTime));
    }

}
