package com.epam.rd.autocode.factory.plot;

import com.epam.rd.autocode.factory.plot.ConcretePlotFactories.ClassicDisneyPlotFactory;
import com.epam.rd.autocode.factory.plot.ConcretePlotFactories.ContemporaryDisneyPlotFactory;
import com.epam.rd.autocode.factory.plot.ConcretePlotFactories.MarvelPlotFactory;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicDisneyPlotFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryDisneyPlotFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new MarvelPlotFactory(heroes, epicCrisis, villain);
    }
}
