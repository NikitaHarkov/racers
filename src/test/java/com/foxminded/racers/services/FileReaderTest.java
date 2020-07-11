package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTest {
    File abbreviations = new File("src/test/resources/abbreviations.txt");
    File starts = new File("src/test/resources/start.log");
    File empty = new File("src/test/resources/empty.log");
    File notExist = new File("nothing");
    FileReader fileReader = new FileReader();

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.parseRacersFromFile(null));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenFileIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.parseRacersFromFile(empty));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenFileNotExists() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.parseRacersFromFile(notExist));
    }

    @Test
    void parseRacersFromFile_ShouldThrowException_WhenGivenWrongFile() {

    }

    @Test
    void parseRacersFromFile_ShouldReturnListOfRacers_WhenGivenFileWithRacers() throws IOException {
        List<Racer> actual = fileReader.parseRacersFromFile(abbreviations);
        List<Racer> expected = Arrays.asList(
                new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new Racer("LHM", "Lewis Hamilton", "MERCEDES"));
        assertEquals(actual, expected);
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.getStartEventTime(null));
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenFileIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.getStartEventTime(empty));
    }

    @Test
    void getStartEventTime_ShouldThrowException_WhenFileNotExists() {
        assertThrows(IllegalArgumentException.class, () -> fileReader.getStartEventTime(notExist));
    }

    @Test
    void getStartEventTie_ShouldReturnEventTimeMap_WhenGivenFileWithTime() throws IOException {
        Map<String, Long> actual = fileReader.getStartEventTime(starts);
        Map<String, Long> expected = new HashMap<>();
        expected.put("SVF", 1527152578917L);
        expected.put("DRR", 1527153252054L);
        expected.put("LHM", 1527153500125L);
        assertEquals(expected, actual);
    }
}
