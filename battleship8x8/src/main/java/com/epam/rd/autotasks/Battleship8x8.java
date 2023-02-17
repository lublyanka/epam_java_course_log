package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {

        this.ships = ships;
    }

    public boolean shoot(String shot) {
        //To specify a binary literal, add the prefix 0b or 0B to the number.
        long shootBinaryRepresentation = Long.MIN_VALUE; //0B1000000000000000000000000000000000000000000000000000000000000000L;
        switch (shot.charAt(0)) {
            case 'A':
                break;
            case 'B': {
                shootBinaryRepresentation >>>= 1;
                break;
            }
            case 'C': {
                shootBinaryRepresentation >>>= 2;
                break;
            }
            case 'D': {
                shootBinaryRepresentation >>>= 3;
                break;
            }
            case 'E': {
                shootBinaryRepresentation >>>= 4;
                break;
            }
            case 'F': {
                shootBinaryRepresentation >>>= 5;
                break;
            }
            case 'G': {
                shootBinaryRepresentation >>>= 6;
                break;
            }
            case 'H': {
                shootBinaryRepresentation >>>= 7;
                break;
            }
        }
        //System.out.println(Long.toBinaryString(shootBinaryRepresentation));
        shootBinaryRepresentation >>>= (8 * (shot.charAt(1) - 1));
        //System.out.println(Long.toBinaryString(shootBinaryRepresentation));
        shots |= shootBinaryRepresentation;
        return ships == (ships | shootBinaryRepresentation);
    }

    public String state() {
        String shipsAsString = Long.toBinaryString(ships);
        String shotsAsString = Long.toBinaryString(shots);


        if (shipsAsString.length() < 64) {
            shipsAsString = "0".repeat(64 - shipsAsString.length()) + shipsAsString;

        }
        if (shotsAsString.length() < 64) {
            shotsAsString = "0".repeat(64 - shotsAsString.length()) + shotsAsString;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shotsAsString.length(); i++) {
            if (i % 8 == 0) {
                result.append('\n');
            }

            if (shotsAsString.charAt(i) == '0' && shipsAsString.charAt(i) == '0') {
                result.append('.');
            } else if (shotsAsString.charAt(i) == '1' && shipsAsString.charAt(i) == '0') {
                result.append('×');
            } else if (shotsAsString.charAt(i) == '0' && shipsAsString.charAt(i) == '1') {
                result.append('☐');
            } else if (shotsAsString.charAt(i) == '1' && shipsAsString.charAt(i) == '1') {
                result.append('☒');
            }
        }
        return String.valueOf(result);
    }
}
