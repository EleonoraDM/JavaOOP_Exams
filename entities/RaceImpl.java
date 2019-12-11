package entities;

import common.ExceptionMessages;
import entities.interfaces.Race;
import entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int LAPS_MIN_COUNT = 1;

    private String name;
    private int laps;
    private Collection<Rider> riders;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.riders = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.INVALID_NAME, name, NAME_MIN_LENGTH));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < LAPS_MIN_COUNT) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, LAPS_MIN_COUNT));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        if (rider == null) {
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }
        if (!rider.getCanParticipate()) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, rider.getName()));
        }
        if (this.riders.contains(rider)) {
            throw new IllegalArgumentException
                    (String.format(ExceptionMessages.RIDER_ALREADY_ADDED, rider.getName(), this.getName()));
        }
        this.riders.add(rider);
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceImpl race = (RaceImpl) o;
        return name.equals(race.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }*/
}
