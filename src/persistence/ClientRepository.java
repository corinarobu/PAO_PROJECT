package persistence;

import model.Client;

import java.util.Arrays;

public class ClientRepository implements GenericRepository<Client> {

    private Client[] storage = new Client[10];

    @Override
    public void add(Client entity) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = entity;
                return;
            }
        }

        Client[] newStorage = Arrays.copyOf(storage, 2 * storage.length);

        newStorage[storage.length] = entity;
        storage = newStorage;
    }

    @Override
    public Client get(int index) {
        return storage[index];
    }

    @Override
    public void update(Client entity) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].getId() == entity.getId()) {
                storage[i] = entity;
                return;
            }
        }
        System.out.println("Client with ID " + entity.getId() + " not found. Update failed.");
    }

    @Override
    public void delete(Client entity) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].getId() == entity.getId()) {
                storage[i] = null;
                return;
            }
        }
        System.out.println("Client with ID " + entity.getId() + " not found. Deletion failed.");
    }


    @Override
    public int getSize() {
        return storage.length;
    }
}
