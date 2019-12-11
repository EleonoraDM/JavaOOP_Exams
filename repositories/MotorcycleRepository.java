package repositories;

import entities.interfaces.Motorcycle;
import repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MotorcycleRepository implements Repository {
    private Collection<Motorcycle> models;

    public MotorcycleRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Object getByName(String name) {
       return this.models.
               stream().
               filter(motorcycle -> motorcycle.getModel().equals(name)).
               findFirst().
               orElse(null);
    }

    @Override
    public Collection getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Object model) {
        this.models.add((Motorcycle) model);
    }

    @Override
    public boolean remove(Object model) {
        return this.models.remove((Motorcycle) model);
    }
}
