package persistence;

import model.Food;

import java.util.HashMap;
import java.util.Map;

public class FoodRepository implements GenericRepository<Food> {

    private Map<Integer, Food> foodMap = new HashMap<>();

    @Override
    public void add(Food entity) {
        foodMap.put(entity.getId(), entity);
    }

    @Override
    public Food get(int id) {
        return foodMap.get(id);
    }

    @Override
    public void update(Food entity) {
        foodMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Food entity) {
        foodMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return foodMap.size();
    }
}
