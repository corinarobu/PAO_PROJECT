package persistence;

import model.Client;
import model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeRepository implements GenericRepository<Employee> {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    @Override
    public void add(Employee entity) {
        employeeMap.put(entity.getId(), entity);
    }

    @Override
    public Employee get(int id) {
        return employeeMap.get(id);
    }

    @Override
    public void update(Employee entity) {
        employeeMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(Employee entity) {
        employeeMap.remove(entity.getId());
    }

    @Override
    public int getSize() {
        return employeeMap.size();
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

}
