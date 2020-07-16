package com.foxminded.racers.services;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LapDuration {
    public Map<String, Long> calculateLapTime(Map<String, Long> startEvent, Map<String, Long> endEvent) {
        checkMaps(startEvent, endEvent);
        return Stream.of(endEvent, startEvent)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Math::subtractExact
                ));
    }

    private void checkMaps(Map<String, Long> startEvent, Map<String, Long> endEvent) {
        if(startEvent==null && endEvent==null){
            throw new IllegalArgumentException("Null is not allowed");
        }
        if(startEvent.isEmpty() && endEvent.isEmpty()){
            throw new IllegalArgumentException("Empty maps are not allowed");
        }
    }
}
