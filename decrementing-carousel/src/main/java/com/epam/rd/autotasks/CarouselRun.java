package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {
    //static CarouselRun instance;
    protected final int[] carousel ;

    protected int currentPosition = 0;


    public CarouselRun(int[] carousel) {
        this.carousel = carousel.clone();
    }

    public int next() {

        if (isFinished())
            return -1;
        else {
            int res = currentPosition = currentPosition % carousel.length;
            while (carousel[currentPosition %= carousel.length] <= 0) {
                currentPosition++;
            }
            carousel[currentPosition]--;
            currentPosition++;
        }
        int result = carousel[currentPosition - 1] + 1;
        return result;
    }

    public boolean isFinished() {
        return !Arrays.stream(carousel).anyMatch(x -> x > 0);
    }

    /*public static CarouselRun getInstance(int[] carousel) {
        if (instance == null)
            instance = new CarouselRun(carousel);
        return instance;
    }*/

    /*public static void reset() {
        instance = null;
    }*/
}
