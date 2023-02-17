package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel {
    protected final int actionLimit;

    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        this.actionLimit = actionLimit;
    }

    @Override
    public CarouselRun run() {
        if (!isOnRun) {
            isOnRun = true;
            return new DecrementingCarouselWithLimitedRunRun(carousel, actionLimit);
        } else {
            return null;
        }
    }
}
