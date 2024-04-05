package persistence;

import model.Review;

import java.util.HashMap;
import java.util.Map;

public class ReviewRepository implements GenericRepository<Review> {

    private Map<Integer, Review> reviewMap = new HashMap<>();

    @Override
    public void add(Review entity) {
        reviewMap.put(entity.getId(), entity);
    }

    @Override
    public Review get(int id) {
        return reviewMap.get(id);
    }

    @Override
    public void update(Review entity) {
        reviewMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Review entity) {
        reviewMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return reviewMap.size();
    }
}
