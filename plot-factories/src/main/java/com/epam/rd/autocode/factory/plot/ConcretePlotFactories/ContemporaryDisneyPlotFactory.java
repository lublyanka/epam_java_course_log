package com.epam.rd.autocode.factory.plot.ConcretePlotFactories;

import com.epam.rd.autocode.factory.plot.*;
import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.Plots.ContemporaryDisneyPlot;

public class ContemporaryDisneyPlotFactory implements PlotFactory {

    private final Character hero;
    private final Character funnyFriend;
    private final EpicCrisis epicCrisis;

    public ContemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }
    @Override
    public Plot plot() {
        return new ContemporaryDisneyPlot(hero, epicCrisis, funnyFriend);
    }
}
