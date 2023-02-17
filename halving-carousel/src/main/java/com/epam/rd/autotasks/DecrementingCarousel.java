package com.epam.rd.autotasks;

public class DecrementingCarousel {
    public static int[] carousel;
    private int nextPosition =0;
    public boolean isOnRun=false;

    public DecrementingCarousel(int capacity) {
        carousel = new int[capacity];
    }

    public boolean addElement(int element) {
        if (element <= 0) {
            return false;
            //throw new UnsupportedOperationException("The new element should be greater than zero");
        } else if (nextPosition > carousel.length) {
            return false;
            //throw new UnsupportedOperationException("The maximum capacity of carousel <" + carousel.length + "> is achieved");
        } else if (isOnRun) {
            return false;
            //throw new UnsupportedOperationException("The carousel is on run. Adding elements is not allowed");
        } else {
            try {
                carousel[nextPosition] = element;
                nextPosition++;
                return true;
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }
        }
    }

    public CarouselRun run() {
        if (!isOnRun) {
            isOnRun = true;
            //return CarouselRun.getInstance(); -> check why test empty case does not work here, timing?
            return new CarouselRun();
        } else {
            return null;
        }
    }
}
