package persistence;

import model.Drink;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements GenericRepository<User> {
    private Map<Integer, User> userMap = new HashMap<>();

    @Override
    public void add(User entity) {
        userMap.put(entity.getId(), entity);
    }

    @Override
    public User get(int id) {
        return userMap.get(id);
    }

    @Override
    public void update(User entity) {
        userMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(User entity) {
        userMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return userMap.size();
    }
}
