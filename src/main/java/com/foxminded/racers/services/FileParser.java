package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileParser {
    private static final int ABBREVIATION_END = 3;

    public List<Racer> parseRacersFromFile(File file) throws IOException {
        checkFile(file);
        Stream<String> dataFromFile = Files.lines(Paths.get(file.getAbsolutePath()));
        return dataFromFile.map(line -> line.split("_"))
                .map(racer -> new Racer(racer[0], racer[1], racer[2]))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getStartEventTime(File file) throws IOException {
        checkFile(file);
        return parseEventTimeFromFile(file);
    }

    public Map<String, Long> getEndEventTime(File file) throws IOException {
        checkFile(file);
        return parseEventTimeFromFile(file);
    }

    private void checkFile(File file) {
        checkIfNull(file);
        checkFileForExistence(file);
        checkFileForEmptiness(file);
    }

    private void checkIfNull(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Null is not allowed");
        }
    }

    private void checkFileForExistence(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("File doesn't exists");
        }
    }

    private void checkFileForEmptiness(File file) {
        if (file.length() == 0) {
            throw new IllegalArgumentException("File is empty");
        }
    }

    private Map<String, Long> parseEventTimeFromFile(File filePath) throws IOException {
        Stream<String> dataFromFile = Files.lines(Paths.get(filePath.getAbsolutePath()));
        return dataFromFile
                .collect(Collectors.toMap(key -> key.substring(0, ABBREVIATION_END), value -> {
                    String date = value.substring(ABBREVIATION_END);
                    LocalDateTime localDateTime = LocalDateTime.parse(date,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
                    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                }));
    }
}
