package io;

import core.ChampionshipControllerImpl;
import core.interfaces.ChampionshipController;
import repositories.MotorcycleRepository;
import repositories.RaceRepository;
import repositories.RiderRepository;

public class CommandsProcessing {
    private ChampionshipController controller =
            new ChampionshipControllerImpl(new RiderRepository(), new MotorcycleRepository(), new RaceRepository());


    public String commandExecution(String command, String[] data) {
        String result = null;

        switch (command) {
            case "CreateRider":
                result = createRider(data);
                break;
            case "CreateMotorcycle":
                result = createMotorcycle(data);
                break;
            case "AddMotorcycleToRider":
                result = addMotorcycleToRider(data);
                break;
            case "AddRiderToRace":
                result = addRiderToRace(data);
                break;
            case "CreateRace":
                result = createRace(data);
                break;
            case "StartRace":
                result = startRace(data);
                break;
            case "End":
                break;
        }
        return result;
    }

    private String createRider(String[] data) {
        String riderName = data[0];
        return this.controller.createRider(riderName);
    }

    private String createMotorcycle(String[] data) {
        String type = data[0];
        String model = data[1];
        int horsepower = Integer.parseInt(data[2]);
        return this.controller.createMotorcycle(type, model, horsepower);
    }

    private String addMotorcycleToRider(String[] data) {
        String riderName = data[0];
        String motorcycleModel = data[1];
        return this.controller.addMotorcycleToRider(riderName, motorcycleModel);
    }

    private String addRiderToRace(String[] data) {
        String raceName = data[0];
        String riderName = data[1];
        return this.controller.addRiderToRace(raceName, riderName);
    }

    private String createRace(String[] data) {
        String name = data[0];
        int laps = Integer.parseInt(data[1]);
        return this.controller.createRace(name, laps);
    }

    private String startRace(String[] data) {
        String raceName = data[0];
        return this.controller.startRace(raceName);
    }
}
