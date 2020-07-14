package com.foxminded.racers.services;

import com.foxminded.racers.model.Racer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultTableTest {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    File abbreviations = new File(classLoader.getResource("abbreviations.txt").getFile());
    File starts = new File(classLoader.getResource("start.log").getFile());
    File ends = new File(classLoader.getResource("end.log").getFile());
    File abbreviations_19 = new File(classLoader.getResource("abbreviations19.txt").getFile());
    File starts_19 = new File(classLoader.getResource("start19.log").getFile());
    File ends_19 = new File(classLoader.getResource("end19.log").getFile());

    RacersResults racersResults = new RacersResults();
    ResultTable resultTable = new ResultTable();

    @Test
    void formatListOfRacers_ShouldThrowException_WhenGivenNull() {
        assertThrows(IllegalArgumentException.class, () -> resultTable.formatRacersResults(null));
    }

    @Test
    void formatListOfRacers_ShouldThrowException_WhenGivenEmptyList() {
        assertThrows(IllegalArgumentException.class, () -> resultTable.formatRacersResults(new ArrayList<>()));
    }

    @Test
    void formatListOfRacers_ShouldReturnFormattedList_WhenGivenCorrectList() throws IOException {
        List<Racer> createdData = racersResults.createListOfRacers(abbreviations, starts, ends);
        List<String> actual = resultTable.formatRacersResults(createdData);
        List<String> expected = Arrays.asList(
                "1.  Sebastian Vettel|FERRARI                  |01:04.415",
                "2.  Daniel Ricciardo|RED BULL RACING TAG HEUER|01:12.013",
                "3.  Lewis Hamilton  |MERCEDES                 |01:12.460");
        assertEquals(expected, actual);
    }

    @Test
    void formatListOfRacers_ShouldReturnFormattedListWithSeperatedLine_WhenGivenCorrectList() throws IOException {
        List<Racer> createdData = racersResults.createListOfRacers(abbreviations_19, starts_19, ends_19);
        List<String> actual = resultTable.formatRacersResults(createdData);
        List<String> expected = Arrays.asList(
                "1.  Sebastian Vettel |FERRARI                  |01:04.415",
                "2.  Daniel Ricciardo |RED BULL RACING TAG HEUER|01:12.013",
                "3.  Valtteri Bottas  |MERCEDES                 |01:12.434",
                "4.  Lewis Hamilton   |MERCEDES                 |01:12.460",
                "5.  Stoffel Vandoorne|MCLAREN RENAULT          |01:12.463",
                "6.  Kimi Raikkonen   |FERRARI                  |01:12.639",
                "7.  Fernando Alonso  |MCLAREN RENAULT          |01:12.657",
                "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        |01:12.706",
                "9.  Charles Leclerc  |SAUBER FERRARI           |01:12.829",
                "10. Sergio Perez     |FORCE INDIA MERCEDES     |01:12.848",
                "11. Romain Grosjean  |HAAS FERRARI             |01:12.930",
                "12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA|01:12.941",
                "13. Carlos Sainz     |RENAULT                  |01:12.950",
                "14. Esteban Ocon     |FORCE INDIA MERCEDES     |01:13.028",
                "15. Nico Hulkenberg  |RENAULT                  |01:13.065",
                "--------------------------------------------------------",
                "16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA|01:13.179",
                "17. Marcus Ericsson  |SAUBER FERRARI           |01:13.265",
                "18. Lance Stroll     |WILLIAMS MERCEDES        |01:13.323",
                "19. Kevin Magnussen  |HAAS FERRARI             |01:13.393");
        assertEquals(expected, actual);
    }
}
