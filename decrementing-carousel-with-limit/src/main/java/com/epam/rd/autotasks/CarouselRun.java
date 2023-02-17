package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {
    protected final int[] carousel;
    int intLeft;


    protected int currentPosition = 0;


    public CarouselRun(int[] carousel) {
        this.carousel = carousel.clone();
        this.intLeft = (int) Arrays.stream(carousel).filter(x -> x > 0).count();
    }

    public int next() {

        if (isFinished())
            return -1;
        else {
            while (carousel[currentPosition %= carousel.length] <= 0) {
                currentPosition++;
            }
            carousel[currentPosition]--;
            if (carousel[currentPosition]<=0)
                intLeft--;
            currentPosition++;
            return carousel[currentPosition - 1] + 1;
        }
    }

    public boolean isFinished() {
        return intLeft <= 0;
    }
}
