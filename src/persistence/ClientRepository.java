package persistence;

import model.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Client getClientById(int clientId) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].getId() == clientId) {
                return storage[i];
            }
        }
        return null;
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        for (Client client : storage) {
            if (client != null) {
                clients.add(client);
            }
        }
        return clients;
    }


    public int getNextId() {
        int maxId = 0;
        for (Client client : storage) {
            if (client.getId() > maxId) {
                maxId = client.getId();
            }
        }

        return maxId + 1;
    }

    public boolean checkClientExists(int clientId) {
        for (Client client : storage) {
            if (client != null && client.getId() == clientId) {
                return true;
            }
        }
        return false;
    }

    public Client getClient(int clientId) {
        for (Client client : storage) {
            if (client != null && client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    public void updateClient(int clientId, Client updatedClient) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && storage[i].getId() == clientId) {
                storage[i] = updatedClient;
                return;
            }
        }
        System.out.println("Client with ID " + clientId + " not found. Update failed.");
    }
}
