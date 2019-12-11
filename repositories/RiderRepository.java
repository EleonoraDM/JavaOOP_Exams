package repositories;

import entities.interfaces.Rider;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RiderRepository implements Repository {
    private Collection<Rider> riders;

    public RiderRepository() {
        this.riders = new ArrayList<>();
    }

    @Override
    public Object getByName(String name) {
        return this.riders.
                stream().
                filter(rider -> rider.getName().equals(name)).
                findFirst().
                orElse(null);
    }

    @Override
    public Collection getAll() {
        return Collections.unmodifiableCollection(this.riders);
    }

    @Override
    public void add(Object model) {
        this.riders.add((Rider) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.riders.remove((Rider) model);
    }
}
