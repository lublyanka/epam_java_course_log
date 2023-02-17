package com.epam.rd.autotasks;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
    }

    @Override
    public CarouselRun run() {
        if (!isOnRun) {
            isOnRun = true;
            return new HalvingRun();
        } else {
            return null;
        }
    }

}
