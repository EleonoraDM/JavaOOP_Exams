package repositories;

import entities.interfaces.Race;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository {
    private Collection<Race> races;

    public RaceRepository() {
        this.races = new ArrayList<>();
    }

    @Override
    public Object getByName(String name) {
        return this.races.
                stream().
                filter(race -> race.getName().equals(name)).
                findFirst().
                orElse(null);
    }

    @Override
    public Collection getAll() {
        return Collections.unmodifiableCollection(this.races);
    }

    @Override
    public void add(Object model) {
        this.races.add((Race) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.races.remove((Race) model);
    }
}
