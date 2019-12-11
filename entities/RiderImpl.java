package entities;

import common.ExceptionMessages;
import common.OutputMessages;
import entities.interfaces.Motorcycle;
import entities.interfaces.Rider;

public class RiderImpl implements Rider {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int WINS_INCREASE_VALUE = 1;

    private String name;
    private Motorcycle motorcycle;
    private int numberOfWins;
    private boolean canParticipate;

    public RiderImpl(String name) {
        this.setName(name);
        this.motorcycle = null;
        this.numberOfWins = 0;
        this.canParticipate = false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Motorcycle getMotorcycle() {
        return this.motorcycle;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.INVALID_NAME, name, NAME_MIN_LENGTH));
        }
        this.name = name;
    }

    private void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    @Override
    public void addMotorcycle(Motorcycle motorcycle) {
        if (motorcycle == null) {
            throw new NullPointerException(ExceptionMessages.MOTORCYCLE_INVALID);
        }
        this.motorcycle = motorcycle;
        this.canParticipate = true;
    }

    @Override
    public void winRace() {
        this.setNumberOfWins(this.getNumberOfWins() + WINS_INCREASE_VALUE);
    }
}
