package core;

import common.ExceptionMessages;
import common.OutputMessages;
import core.interfaces.ChampionshipController;
import entities.PowerMotorcycle;
import entities.RaceImpl;
import entities.RiderImpl;
import entities.SpeedMotorcycle;
import entities.interfaces.Motorcycle;
import entities.interfaces.Race;
import entities.interfaces.Rider;
import repositories.MotorcycleRepository;
import repositories.RaceRepository;
import repositories.RiderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {
    private static final int MIN_NUM_RIDERS = 3;

    private RiderRepository riderRepository;
    private MotorcycleRepository motorcycleRepository;
    private RaceRepository raceRepository;

    public ChampionshipControllerImpl
            (RiderRepository riderRepository, MotorcycleRepository motorcycleRepository, RaceRepository raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {
        Rider rider = (Rider) this.riderRepository.getByName(riderName);
        if (rider != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_EXISTS, riderName));
        }
        rider = new RiderImpl(riderName);
        this.riderRepository.add(rider);
        return String.format(OutputMessages.RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle = (Motorcycle) this.motorcycleRepository.getByName(model);

        if (motorcycle != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.MOTORCYCLE_EXISTS, model));
        }
        if (type.equals("Speed")) {
            motorcycle = new SpeedMotorcycle(model, horsePower);
        } else {
            motorcycle = new PowerMotorcycle(model, horsePower);
        }
        this.motorcycleRepository.add(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_CREATED, motorcycle.getClass().getSimpleName(), model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = (Rider) this.riderRepository.getByName(riderName);
        Motorcycle motorcycle = (Motorcycle) this.motorcycleRepository.getByName(motorcycleModel);

        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        if (motorcycle == null) {
            throw new NullPointerException(String.format(ExceptionMessages.MOTORCYCLE_NOT_FOUND, motorcycleModel));
        }
        rider.addMotorcycle(motorcycle);
        return String.format(OutputMessages.MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = (Race) this.raceRepository.getByName(raceName);
        Rider rider = (Rider) this.riderRepository.getByName(riderName);

        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }
        if (rider == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RIDER_NOT_FOUND, riderName));
        }
        race.addRider(rider);
        return String.format(OutputMessages.RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = (Race) this.raceRepository.getByName(name);

        if (race != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS, name));
        }
        race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        StringBuilder sb = new StringBuilder();
        Race race = (Race) this.raceRepository.getByName(raceName);

        if (race == null) {
            throw new NullPointerException(String.format(ExceptionMessages.RACE_NOT_FOUND, raceName));
        }

        if (race.getRiders().size() < MIN_NUM_RIDERS) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, MIN_NUM_RIDERS));
        }

        int laps = race.getLaps();

        List<Rider> winners = race.getRiders().
                stream().
                sorted((r1, r2) -> Double.compare
                        (r2.getMotorcycle().calculateRacePoints(laps), r1.getMotorcycle().calculateRacePoints(laps)))
                .collect(Collectors.toList());

        sb.append(String.format(OutputMessages.RIDER_FIRST_POSITION, winners.get(0).getName(), raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_SECOND_POSITION, winners.get(1).getName(), raceName));
        sb.append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_THIRD_POSITION, winners.get(2).getName(), raceName));

        return sb.toString();
    }
}
