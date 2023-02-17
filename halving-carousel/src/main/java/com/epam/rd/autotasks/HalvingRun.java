package com.epam.rd.autotasks;

import java.util.Arrays;

public class HalvingRun extends CarouselRun {

    @Override
    public int next() {

        if (isFinished())
            return -1;
        else {
            int result;
            while (carousel[currentPosition %= carousel.length] <= 0) {
                currentPosition++;
            }
            result = carousel[currentPosition];
            carousel[currentPosition] /= 2;
            currentPosition++;
            return result;
        }
    }

    @Override
    public boolean isFinished() {
        return !Arrays.stream(carousel).anyMatch(x -> x > 0);
    }
}
