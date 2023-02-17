package com.epam.rd.autotasks;

public class GraduallyDecreasingCarousel extends DecrementingCarousel{
    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (!isOnRun) {
            isOnRun = true;
            return new GraduallyDecreasingCarouselRun(carousel);
        } else {
            return null;
        }
    }
}
