package persistence;

import model.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository implements GenericRepository<Restaurant> {

    private Map<Integer, Restaurant> restaurantMap = new HashMap<>();

    @Override
    public void add(Restaurant entity) {
        restaurantMap.put(entity.getId(), entity);
    }

    @Override
    public Restaurant get(int id) {
        return restaurantMap.get(id);
    }

    @Override
    public void update(Restaurant entity) {
        restaurantMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Restaurant entity) {
        restaurantMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return restaurantMap.size();
    }
}
