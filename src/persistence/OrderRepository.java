package persistence;

import model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository implements GenericRepository<Order> {

    private Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public void add(Order entity) {
        orderMap.put(entity.getId(), entity);
    }

    @Override
    public Order get(int id) {
        return orderMap.get(id);
    }

    @Override
    public void update(Order entity) {
        orderMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Order entity) {
        orderMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return orderMap.size();
    }

    public List<Order> getAllOrders() {return new ArrayList<>(orderMap.values());
    }
}
