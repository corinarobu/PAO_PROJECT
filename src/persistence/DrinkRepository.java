package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Drink;

public class DrinkRepository implements GenericRepository<Drink> {
    private Map<Integer, Drink> drinkMap = new HashMap<>();

    @Override
    public void add(Drink entity) {
        drinkMap.put(entity.getId(), entity);
    }

    @Override
    public Drink get(int id) {
        return drinkMap.get(id);
    }

    @Override
    public void update(Drink entity) {
        drinkMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Drink entity) {
        drinkMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return drinkMap.size();
    }
}
