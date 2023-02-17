package com.epam.rd.autotasks;

import java.util.Arrays;

public class GraduallyDecreasingCarouselRun extends CarouselRun {
    private int decrement = 1;
    private int intLeft;

    public GraduallyDecreasingCarouselRun(int[] carousel) {
        super(carousel);
        intLeft = (int) Arrays.stream(carousel).filter(x -> x > 0).count();
    }

    @Override
    public int next() {
        if (isFinished())
            return -1;
        else {
            int result;
            result = carousel[currentPosition];
            carousel[currentPosition] -= decrement;
            if (carousel[currentPosition] <= 0)
                intLeft--;
            do {
                if (currentPosition == carousel.length - 1)
                    decrement++;
                currentPosition++;
            }
            while (carousel[currentPosition %= carousel.length] <= 0 && !isFinished());
            return result;
        }
    }

    @Override
    public boolean isFinished() {
        return intLeft <= 0;
    }
}
