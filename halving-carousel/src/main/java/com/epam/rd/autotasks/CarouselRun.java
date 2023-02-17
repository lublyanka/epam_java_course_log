package com.epam.rd.autotasks;

import java.util.Arrays;

public class CarouselRun {
    //static CarouselRun instance;
    protected final int[] carousel = DecrementingCarousel.carousel.clone();
    protected int currentPosition = 0;

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

}
