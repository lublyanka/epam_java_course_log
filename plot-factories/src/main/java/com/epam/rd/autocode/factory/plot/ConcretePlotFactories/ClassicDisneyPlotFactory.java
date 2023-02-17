package com.epam.rd.autocode.factory.plot.ConcretePlotFactories;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.Plots.ClassicDisneyPlot;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;

public class ClassicDisneyPlotFactory implements PlotFactory {
    private final Character hero;
    private final Character beloved;
    private final Character villain;

    public ClassicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }
    @Override
    public Plot plot() {
        return new ClassicDisneyPlot(hero, beloved, villain);
    }
}