package entities;

import common.ExceptionMessages;
import entities.interfaces.Motorcycle;

public abstract class MotorcycleImpl implements Motorcycle {
    private static final int LENGTH_VALIDATION = 4;

    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        this.setModel(model);
        this.setHorsePower(horsePower);
        this.cubicCentimeters = cubicCentimeters;
    }

    private void setModel(String model) {
        if (model == null || model.trim().isEmpty() || model.length() < 4) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.INVALID_MODEL, model, LENGTH_VALIDATION));
        }
        this.model = model;
    }

    protected void setHorsePower(int horsePower){
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

}
