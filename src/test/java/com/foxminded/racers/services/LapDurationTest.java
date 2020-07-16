package com.foxminded.racers.services;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LapDurationTest {
    LapDuration lapDuration = new LapDuration();

    @Test
    void calculateLapTime_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> lapDuration.calculateLapTime(null, null));
    }

    @Test
    void calculateLapTime_ShouldThrowException_WhenGivenEmptyMaps() {
        assertThrows(IllegalArgumentException.class, () -> lapDuration.calculateLapTime(new HashMap<>(), new HashMap<>()));
    }

    @Test
    void calculateLapTime_ShouldReturnCalculatedLapTime_WhenStartAndEndEventMapsAreGiven() throws IOException {
        Map<String, Long> startEvent = new HashMap<>();
        startEvent.put("LHM", 1527153500125L);
        startEvent.put("SVF", 1527152578917L);
        startEvent.put("DRR", 1527153252054L);
        Map<String, Long> endEvent = new HashMap<>();
        endEvent.put("LHM", 1527153572585L);
        endEvent.put("SVF", 1527152643332L);
        endEvent.put("DRR", 1527153324067L);
        Map<String, Long> actual = lapDuration.calculateLapTime(startEvent, endEvent);
        Map<String, Long> expected = new HashMap<>();
        expected.put("LHM", 72460L);
        expected.put("SVF", 64415L);
        expected.put("DRR", 72013L);
        assertEquals(expected, actual);
    }

    @Test
    void calculateLapTime_ShouldReturnCalculatedLapTime_WhenIsOneExtraRecord() throws IOException {
        Map<String, Long> startEvent = new HashMap<>();
        startEvent.put("SVF", 1527152578917L);
        startEvent.put("DRR", 1527153252054L);
        Map<String, Long> endEvent = new HashMap<>();
        endEvent.put("LHM", 1527153572585L);
        endEvent.put("SVF", 1527152643332L);
        endEvent.put("DRR", 1527153324067L);
        Map<String, Long> actual = lapDuration.calculateLapTime(startEvent, endEvent);
        Map<String, Long> expected = new HashMap<>();
        expected.put("LHM", 1527153572585L);
        expected.put("SVF", 64415L);
        expected.put("DRR", 72013L);
        assertEquals(expected, actual);
    }
}
