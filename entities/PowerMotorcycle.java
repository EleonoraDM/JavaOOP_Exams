package entities;

import common.ExceptionMessages;

public class PowerMotorcycle extends MotorcycleImpl {
    private static final int MIN_HORSEPOWER = 70;
    private static final int MAX_HORSEPOWER = 100;
    private static final double CUBIC_CENTIMETERS = 450;


    public PowerMotorcycle(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower < MIN_HORSEPOWER || horsePower > MAX_HORSEPOWER) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }

    //cubic centimeters / horsepower * laps
    @Override
    public double calculateRacePoints(int laps) {
        double racePoints = CUBIC_CENTIMETERS / super.getHorsePower() * laps;
        return racePoints;
    }
}

