package com.foxminded.racers.services;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LapDurationTest {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    File starts = new File(classLoader.getResource("start.log").getFile());
    File end = new File(classLoader.getResource("end.log").getFile());

    LapDuration lapDuration = new LapDuration();
    FileParser fileParser = new FileParser();

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
        Map<String, Long> startEvent = fileParser.getStartEventTime(starts);
        Map<String, Long> endEvent = fileParser.getEndEventTime(end);
        Map<String, Long> actual = lapDuration.calculateLapTime(startEvent,endEvent);
        Map<String, Long> expected = new HashMap<>();
        expected.put("LHM", 72460L);
        expected.put("SVF", 64415L);
        expected.put("DRR", 72013L);
        assertEquals(expected,actual);
    }
}
