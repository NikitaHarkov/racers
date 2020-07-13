package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacersResultsTest {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
    File starts = new File(classLoader.getResource("start.log").getFile());
    File end = new File(classLoader.getResource("end.log").getFile());

    RacersResults racersResults = new RacersResults();

    @Test
    void createListOfRacers_ShouldThrowException_WhenFilesAreNull() {
        assertThrows(IllegalArgumentException.class, () -> racersResults.createListOfRacers(null, null, null));
    }

    @Test
    void createListOfRacers_ShouldReturnSortedListOfRacersWithLapTime_WhenGivenCorrectFiles() throws IOException {
        List<Racer> actual = racersResults.createListOfRacers(abbreviations, starts, end);
        List<Racer> expected = Arrays.asList(
                new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new Racer("LHM", "Lewis Hamilton", "MERCEDES"));
        expected.get(0).setLapTime(64415L);
        expected.get(1).setLapTime(72013L);
        expected.get(2).setLapTime(72460L);
        assertEquals(expected, actual);
    }
}
