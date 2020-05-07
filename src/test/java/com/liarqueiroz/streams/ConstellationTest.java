package com.liarqueiroz.streams;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
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
}
