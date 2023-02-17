package com.epam.rd.autotasks;

public class DecrementingCarouselWithLimitedRunRun extends CarouselRun {
    protected int actionLimit;

    public DecrementingCarouselWithLimitedRunRun(int[] carousel, int actionLimit) {
        super(carousel);
        this.actionLimit = actionLimit;
    }

    @Override
    public int next() {
        int result = super.next();
        actionLimit--;
        return result;
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || actionLimit <= 0;
    }
}