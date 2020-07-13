package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParserTest {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
    File starts = new File(classLoader.getResource("start.log").getFile());
    File empty = new File(classLoader.getResource("empty.log").getFile());
    File notExist = new File("nothing");
    FileParser fileParser = new FileParser();

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.parseRacersFromFile(null));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenFileIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.parseRacersFromFile(empty));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenFileNotExists() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.parseRacersFromFile(notExist));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenGivenWrongFile() {

    }

    @Test
    void parseRacersFromFile_ShouldReturnListOfRacers_WhenGivenFileWithRacers() throws IOException {
        List<Racer> actual = fileParser.parseRacersFromFile(abbreviations);
        List<Racer> expected = Arrays.asList(
                new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new Racer("LHM", "Lewis Hamilton", "MERCEDES"));
        assertEquals(actual, expected);
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.getStartEventTime(null));
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenFileIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.getStartEventTime(empty));
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenFileNotExists() {
        assertThrows(IllegalArgumentException.class, () -> fileParser.getStartEventTime(notExist));
    }

    @Test
    void getStartEventTie_ShouldReturnEventTimeMap_WhenGivenFileWithTime() throws IOException {
        Map<String, Long> actual = fileParser.getStartEventTime(starts);
        Map<String, Long> expected = new HashMap<>();
        expected.put("SVF", 1527152578917L);
        expected.put("DRR", 1527153252054L);
        expected.put("LHM", 1527153500125L);
        assertEquals(expected, actual);
    }
}
