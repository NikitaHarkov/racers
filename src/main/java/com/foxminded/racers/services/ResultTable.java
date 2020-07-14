package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ResultTable {
    private static final char VERTICAL_LINE = '|';
    private static final char DOT = '.';
    private static final int DOT_LENGTH = 1;
    private static final int SPACE_LENGTH = 1;
    private static final int VERTICAL_LINE_LENGTH = 1;
    private static final int TOP_SIZE = 15;
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String DURATION_FORMAT = "mm:ss.SSS";

    public List<String> formatRacersResults(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException("Null is not allowed");
        }
        if(racers.isEmpty()){
            throw new IllegalArgumentException("Empty list not allowed");
        }
        List<String> result = new ArrayList<>();
        addLapsData(result,racers);
        printResult(result);
        return result;
    }

    private void printResult(List<String> result) {
        result.forEach(System.out::println);
    }

    private void addLapsData(List<String> result, List<Racer> racers) {
        for (int i = 0; i < racers.size(); i++) {
            if (i == TOP_SIZE) {
                result.add(getDashesLine(racers));
            }
            result.add(getRacerLine(racers.get(i), i, racers));
        }
    }

    private String getDashesLine(List<Racer> racers) {
        int positionPartLength = getPositionLength(racers);
        int racerPartLength = getMaxRacerNameLength(racers) + VERTICAL_LINE_LENGTH;
        int teamPartLength = getMaxTeamNameLength(racers) + VERTICAL_LINE_LENGTH;
        int durationPartLength = DURATION_FORMAT.length();

        int dashesLineLength = positionPartLength + racerPartLength + teamPartLength + durationPartLength;
        return addSymbolsToString(DASH, dashesLineLength, DASH);
    }

    private String getRacerLine(Racer racer, int position, List<Racer> racers) {
        StringBuilder racerData = new StringBuilder();
        racerData.append(getPositionPart(position));
        racerData.append(getRacerPart(racer, racers));
        racerData.append(getTeamPart(racer, racers));
        racerData.append(getLapTime(racer));
        return racerData.toString();
    }

    private String getPositionPart(int position) {
        StringBuilder positionPart = new StringBuilder();
        positionPart.append(position + 1);
        positionPart.append(DOT);
        if (((position + 1) / 10) > 0) {
            positionPart.append(SPACE);
        } else {
            positionPart.append(SPACE + SPACE);
        }
        return positionPart.toString();
    }

    private int getPositionLength(List<Racer> racers) {
        return ((int) (Math.log10(racers.size()))) + DOT_LENGTH + SPACE_LENGTH;
    }

    private String getRacerPart(Racer racer, List<Racer> racers) {
        StringBuilder racerPart = new StringBuilder();
        racerPart.append(addSymbolsToString(racer.getName(), getMaxRacerNameLength(racers), SPACE));
        racerPart.append(VERTICAL_LINE);
        return racerPart.toString();
    }

    private String getTeamPart(Racer racer, List<Racer> racers) {
        StringBuilder teamPart = new StringBuilder();
        teamPart.append(addSymbolsToString(racer.getTeam(), getMaxTeamNameLength(racers), SPACE));
        teamPart.append(VERTICAL_LINE);
        return teamPart.toString();
    }

    private String getLapTime(Racer racer) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        return formatter.format(racer.getLapTime());
    }

    private int getMaxRacerNameLength(List<Racer> racers) {
        OptionalInt max = racers.stream()
                .map(Racer::getName)
                .mapToInt(String::length)
                .max();
        return max.orElse(0);
    }

    private int getMaxTeamNameLength(List<Racer> racers) {
        OptionalInt max = racers.stream()
                .map(Racer::getTeam)
                .mapToInt(String::length)
                .max();
        return max.orElse(0);
    }

    private String addSymbolsToString(String string, int totalLength, String symbol) {
        StringBuilder result = new StringBuilder(string);
        int spaces = totalLength - string.length();
        result.append(String.valueOf(symbol).repeat(Math.max(0, spaces)));
        return result.toString();
    }
}
