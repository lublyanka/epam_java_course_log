package com.epam.rd.autocode.factory.plot.Plots;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MarvelPlot  implements Plot {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;

        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    private String getHeroEndingString(Character[] heroes) {
        return heroes.length == 1 ? "the inflexible hero." : "inflexible heroes.";
    }

    private String getHeroesCharacters() {
        StringBuilder sb = new StringBuilder(Arrays.stream(heroes).map(x -> " the brave " + x.name()).collect(Collectors.joining(",")));
        if (heroes.length > 1) {
            sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, " and");
            sb.append(" are");
        } else sb.append(" is");
        return sb.toString();
    }

    @Override
    public String asText() {
            return epicCrisis.name() +
                    " threatens the world. But" +
                    getHeroesCharacters() +
                    " on guard. So, no way that intrigues of " +
                    villain.name() +
                    " will bend the willpower of " +
                    getHeroEndingString(heroes);
        }
}