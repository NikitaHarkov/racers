package com.foxminded.racers.services;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LapDurationTest {
    File starts = new File("src/test/resources/start.log");
    File end = new File("src/test/resources/end.log");

    LapDuration lapDuration = new LapDuration();
    FileReader fileReader = new FileReader();

    @Test
    void calculateLapTime_ShouldThrowException_WhenGivenNull(){
        assertThrows(IllegalArgumentException.class,()->lapDuration.calculateLapTime(null,null));
    }

    @Test
    void calculateLapTime_ShouldThrowException_WhenGivenEmptyMaps(){
        assertThrows(IllegalArgumentException.class,()->lapDuration.calculateLapTime(new HashMap<>(),new HashMap<>()));
    }

    @Test
    void calculateLapTime_ShouldReturnCalculatedLapTime_WhenStartAndEndEventMapsAreGiven() throws IOException {
        Map<String, Long> startEvent = fileReader.getStartEventTime(starts);
        Map<String, Long> endEvent = fileReader.getEndEventTime(end);
        Map<String, Long> actual = lapDuration.calculateLapTime(startEvent,endEvent);
        Map<String, Long> expected = new HashMap<>();
        expected.put("LHM", 72460L);
        expected.put("SVF", 64415L);
        expected.put("DRR", 72013L);
        assertEquals(expected,actual);
    }
}
