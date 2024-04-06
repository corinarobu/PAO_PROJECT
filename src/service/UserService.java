package service;

import model.Client;
import model.Employee;
import model.Review;
import persistence.ClientRepository;
import persistence.EmployeeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private Scanner scanner;
    private List<Client> clientList;
    private List<Employee> employeeList;

    private List<Review> reviewList = new ArrayList<>();




    public UserService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.scanner = new Scanner(System.in);
        this.clientList = clientRepository.getAllClients();
        this.employeeList = employeeRepository.getAllEmployees();
    }

    public void addUser() {
        System.out.println("Selectati tipul de utilizator:");
        System.out.println("1. Client");
        System.out.println("2. Angajat");

        int option = readOption();
        switch (option) {
            case 1:
                addClient();
                break;
            case 2:
                addEmployee();
                break;
            default:
                System.out.println("Optiune invalida.");
                break;
        }
    }

    private int readOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Optiune invalida. Introduceti un numar.");
            return readOption();
        }
    }

    public void addClient() {
        System.out.println("Introduceți numele clientului:");
        String username = scanner.nextLine();
        System.out.println("Introduceți adresa email a clientului:");
        String email = scanner.nextLine();
        System.out.println("Introduceți parola clientului:");
        String password = scanner.nextLine();
        System.out.println("Introduceți numărul de telefon al clientului:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Introduceți vârsta clientului:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduceți adresa clientului:");
        String address = scanner.nextLine();

        Client newClient = new Client(username, email, password, phoneNumber, age, address);
        clientRepository.add(newClient);
        System.out.println("Client adăugat cu succes.");
    }

    public void addEmployee() {
        System.out.println("Introduceți numele angajatului:");
        String username = scanner.nextLine();
        System.out.println("Introduceți adresa email a angajatului:");
        String email = scanner.nextLine();
        System.out.println("Introduceți parola angajatului:");
        String password = scanner.nextLine();
        System.out.println("Introduceți numărul de telefon al angajatului:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Introduceți vârsta angajatului:");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduceți titlul jobului:");
        String jobTitle = scanner.nextLine();

        System.out.println("Introduceți data angajării");
        long hiringDateMillis = Long.parseLong(scanner.nextLine());
        Date hiringDate = new Date(hiringDateMillis);

        System.out.println("Introduceți salariul angajatului:");
        double salary = Double.parseDouble(scanner.nextLine());
        System.out.println("Introduceți numărul de ore de lucru zilnice ale angajatului:");
        int dailyWorkHours = Integer.parseInt(scanner.nextLine());

        Employee newEmployee = new Employee(username, email, password, phoneNumber, age, jobTitle, hiringDate, salary, dailyWorkHours);
        employeeRepository.add(newEmployee);
        System.out.println("Angajat adăugat cu succes.");
    }

    public void showUsers() {
        List<Client> clients = clientRepository.getAllClients();
        List<Employee> employees = employeeRepository.getAllEmployees();

        System.out.println("Clients:");
        for (Client client : clients) {
            System.out.println(client);
        }

        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }


    public void updateUserProfile() {
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        if (!clientRepository.checkClientExists(userId) && !employeeRepository.checkEmployeeExists(userId)) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        System.out.println("Enter new user details:");
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        if (clientRepository.checkClientExists(userId)) {
            Client currentClient = clientRepository.getClient(userId);


            currentClient.setPassword(password);
            currentClient.setPhoneNumber(phoneNumber);
            currentClient.setAge(age);

            clientRepository.update(currentClient);
            clientList = clientRepository.getAllClients();

            System.out.println("Client profile updated successfully.");
        } else if (employeeRepository.checkEmployeeExists(userId)) {
            Employee currentEmployee = employeeRepository.getEmployee(userId);


            currentEmployee.setPassword(password);
            currentEmployee.setPhoneNumber(phoneNumber);
            currentEmployee.setAge(age);

            employeeRepository.update(currentEmployee);
            employeeList = employeeRepository.getAllEmployees();

            System.out.println("Employee profile updated successfully.");
        }
    }

    public void deleteUser() {
        System.out.println("Select user type to delete:");
        System.out.println("1. Client");
        System.out.println("2. Employee");

        int userType = readOption();

        switch (userType) {
            case 1:
                deleteClient();
                break;
            case 2:
                deleteEmployee();
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    public void deleteClient() {
        System.out.print("Enter client ID: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        if (!clientRepository.checkClientExists(clientId)) {
            System.out.println("Client with ID " + clientId + " not found.");
            return;
        }

        Client clientToDelete = clientRepository.getClient(clientId);


        System.out.println("Are you sure you want to delete this client? (Y/N)");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {

            clientRepository.delete(clientToDelete);
            clientList = clientRepository.getAllClients();

            System.out.println("Client deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private void deleteEmployee() {
        System.out.print("Enter employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        if (!employeeRepository.checkEmployeeExists(employeeId)) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        }

        // Obținem angajatul specificat
        Employee employeeToDelete = employeeRepository.getEmployee(employeeId);


        System.out.println("Are you sure you want to delete this employee? (Y/N)");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {

            employeeRepository.delete(employeeToDelete);
            employeeList = employeeRepository.getAllEmployees();
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }



    public void leaveReview() {
        System.out.print("Introduceți ID-ul dvs. de client: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        Client client = clientRepository.getClient(clientId);
        if (client == null) {
            System.out.println("Clientul cu ID-ul " + clientId + " nu există.");
            return;
        }

        System.out.print("Introduceți rating-ul (de la 1 la 5): ");
        int rating = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduceți comentariul dvs.: ");
        String comment = scanner.nextLine();

        Review review = new Review(rating, comment, client.getAge());

        client.addReview(review);


        reviewList.add(review);

        System.out.println("Recenzia a fost adăugată cu succes.");
    }


    public void showAllReviews() {

        if (reviewList.isEmpty()) {
            System.out.println("Nu există recenzii disponibile.");
            return;
        }

        System.out.println("Lista de recenzii:");

        for (Review review : reviewList) {
            System.out.println("ID: " + review.getId());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Comentariu: " + review.getComment());
            System.out.println("Vârsta utilizatorului: " + review.getUserAge());
            System.out.println();
        }
    }
}
