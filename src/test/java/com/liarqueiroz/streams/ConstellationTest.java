package com.liarqueiroz.streams;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;

public class ConstellationTest {

    private static Constellation[] constellationArray = {
            new Constellation(1, "Leo", 9, false),
            new Constellation(2, "Centaurus", 11, true),
            new Constellation(3, "Ursa Major", 7, false),
            new Constellation(4, "Pisces", 18, false),
            new Constellation(5, "Scorpius", 18, true),
            new Constellation(6, "Crux", 4, true)
    };

    private static List<Constellation> constellationList = Arrays.asList(constellationArray);

    @Test
    public void whenFilteringVisibleConstellation_thenGetVisibleList() {
        List<Constellation> visibleConstellations = constellationList.stream()
                .filter(Constellation::getVisibleInBrazil)
                .collect(Collectors.toList());

        assertThat(visibleConstellations, contains(
                hasProperty("name", equalTo("Centaurus")),
                hasProperty("name", equalTo("Scorpius")),
                hasProperty("name", equalTo("Crux"))
        ));
    }

    @Test
    public void whenCountingAllStars_thenGetSumOfAllStars() {
        int starsCount = constellationList.stream()
                .map(Constellation::getAmountOfStars)
                .reduce(0, Integer::sum);

        assertEquals(starsCount, 67);
    }

    @Test
    public void whenJoiningNames_thenGetJoinedString() {
        String joinedNames = constellationList.stream()
                .map(Constellation::getName)
                .collect(Collectors.joining(", "));

        assertEquals(joinedNames, "Leo, Centaurus, Ursa Major, Pisces, Scorpius, Crux");
    }

    @Test
    public void whenCountingSouthernConstellation_thenGetCount() {
        Long southernConstellationsCount = constellationList.stream()
                .filter(Constellation::getVisibleInBrazil)
                .count();

        assertEquals(southernConstellationsCount, new Long(3));
    }

    @Test
    public void whenIncrementAmountOfStars_thenGetNewAmount() {
        Constellation[] constellationArray = {
                new Constellation(1, "Leo", 9, false),
                new Constellation(2, "Centaurus", 11, true),
                new Constellation(3, "Ursa Major", 7, false)
        };
        List<Constellation> constellationList = Arrays.asList(constellationArray);

        constellationList.stream()
                .forEach(Constellation::addStar);

        assertThat(constellationList, contains(
                hasProperty("amountOfStars", equalTo(10)),
                hasProperty("amountOfStars", equalTo(12)),
                hasProperty("amountOfStars", equalTo(8))
        ));
    }

    @Test
    public void whenFindFirstSouthernConstellation_thenGetCentaurus() {
        Constellation firstSouthernConstellation = constellationList.stream()
                .filter(Constellation::getVisibleInBrazil)
                .findFirst()
                .orElse(null);

        assertEquals(firstSouthernConstellation.getName(), "Centaurus");
    }

    @Test
    public void whenFindingLargestSouthernConstellation_thenGetScorpius() {
        Constellation largestSouthernConstellation = constellationList.stream()
                .filter(Constellation::getVisibleInBrazil)
                .max(Comparator.comparing(Constellation::getAmountOfStars))
                .orElseGet(null);

        assertEquals(largestSouthernConstellation.getName(), "Scorpius");
    }
}
