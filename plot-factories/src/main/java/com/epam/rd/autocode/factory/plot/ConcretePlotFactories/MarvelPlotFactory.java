package com.epam.rd.autocode.factory.plot.ConcretePlotFactories;

import com.epam.rd.autocode.factory.plot.*;
import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.Plots.MarvelPlot;

public class MarvelPlotFactory implements PlotFactory {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain){
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;

    }

    @Override
    public Plot plot() {
        return new MarvelPlot(heroes, epicCrisis, villain);
    }
}
