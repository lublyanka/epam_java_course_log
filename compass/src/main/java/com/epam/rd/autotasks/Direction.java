package com.epam.rd.autotasks;

import java.util.Arrays;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private final int degrees;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    private static int normalize(int degrees) {
        if (degrees < 0) degrees = 360 + degrees;
        return degrees % 360;
    }

    public static Direction ofDegrees(int degrees) {
        int normalDegree = normalize(degrees);
        return Arrays.stream(Direction.values())
                .filter(x -> x.degrees == normalDegree).findFirst().orElseGet(() -> null);

        /*switch (degrees % 360) {
            case 0:
                return N;
            case 45:
                return NE;
            case 90:
                return E;
            case 135:
                return SE;
            case 180:
                return S;
            case 225:
                return SW;
            case 270:
                return W;
            case 315:
                return NW;
        }*/
        //return null;
    }


    public static Direction closestToDegrees(int degrees) {
        int normalDegree = normalize(degrees);
        return Arrays.stream(Direction.values())
                .filter(x -> x.degrees - 22 <= normalDegree && x.degrees + 23 > normalDegree)
                .findFirst().orElseGet(() -> N);
        /*if (normalDegree < 23)
            return N;
        else if (normalDegree < 68)
            return NE;
        else if (normalDegree < 113)
            return E;
        else if (normalDegree < 158)
            return SE;
        else if (normalDegree < 203)
            return S;
        else if (normalDegree < 248)
            return SW;
        else if (normalDegree < 293)
            return W;
        else if (normalDegree < 338)
            return NW;
        else return N;*/

    }

    public Direction opposite() {
        return ofDegrees(this.degrees + 180);
    }

    public int differenceDegreesTo(Direction direction) {
        return Math.min(Math.abs(this.degrees - direction.degrees),
                360 - Math.abs(this.degrees - direction.degrees));
    }
}
