package jdbc.service;

import jdbc.exceptions.InvalidDataException;
import jdbc.model.App_User;
import jdbc.model.Client;
import jdbc.model.Employee;
import jdbc.model.Review;

import jdbc.persistence.EmployeeRepository;
import jdbc.persistence.ClientRepository;
import jdbc.persistence.ReviewRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private Scanner scanner;

    private List<Review> reviewList = new ArrayList<>();

    private ReviewRepository reviewRepository;

    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public UserService(ClientRepository clientRepository, EmployeeRepository employeeRepository) throws InvalidDataException {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.scanner = new Scanner(System.in);
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


        int lastUserId = clientRepository.getLastUserId();
        int user_id = lastUserId + 1;


        Client client = new Client(user_id, username, email, password, phoneNumber, age, user_id, address);

        clientRepository.add(client);

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
        int hiringDateMillis = Integer.parseInt(scanner.nextLine());
        Date hiringDate = new Date(hiringDateMillis);

        System.out.println("Introduceți salariul angajatului:");
        int salary = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceți numărul de ore de lucru zilnice ale angajatului:");
        int dailyWorkHours = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceți id-ul angajatului:");
        int employee_id = Integer.parseInt(scanner.nextLine());

        int lastUserId = clientRepository.getLastUserId();
        int user_id = lastUserId + 1;

        Employee employee = new Employee(user_id, username, email, password, phoneNumber, age, employee_id, jobTitle, hiringDate, salary, dailyWorkHours);

        employeeRepository.add(employee);
        System.out.println("Angajat adăugat cu succes.");
    }

    public void showUsers() {
        System.out.println("Users:");

        List<Client> clients = clientRepository.getAll();
        for (Client client : clients) {
            System.out.println("Client: " + client);
        }

        List<Employee> employees = employeeRepository.getAll();
        for (Employee employee : employees) {
            System.out.println("Employee: " + employee);
        }
    }

    public void updateUserProfile() {
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        Client client = clientRepository.get(userId);
        Employee employee = employeeRepository.get(userId);

        if (client == null && employee == null) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        // Citirea noilor detalii de utilizator
        System.out.println("Enter new user details:");
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        // Actualizarea profilului utilizatorului curent în baza de date
        if (client != null) {
            client.setPassword(password);
            client.setPhoneNumber(phoneNumber);
            client.setAge(age);
            clientRepository.update(client);
            System.out.println("Client profile updated successfully.");
        } else if (employee != null) {
            employee.setPassword(password);
            employee.setPhoneNumber(phoneNumber);
            employee.setAge(age);
            employeeRepository.update(employee);
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

        Client clientToDelete = clientRepository.get(clientId);
        if (clientToDelete == null) {
            System.out.println("Client with ID " + clientId + " not found.");
            return;
        }

        clientRepository.delete(clientToDelete);
        System.out.println("Client deleted successfully.");
    }

    public void deleteEmployee() {
        System.out.print("Enter employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă angajatul există în baza de date
        Employee employeeToDelete = employeeRepository.get(employeeId);
        if (employeeToDelete == null) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        }

        // Ștergem angajatul din baza de date
        employeeRepository.delete(employeeToDelete);
        System.out.println("Employee deleted successfully.");
    }

    public void leaveReview() {
        System.out.print("Introduceți ID-ul dvs. de client: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă clientul există în baza de date
        Client client = clientRepository.get(clientId);
        if (client == null) {
            System.out.println("Client with ID " + clientId + " not found.");
            return;
        }

        System.out.print("Introduceți rating-ul (de la 1 la 5): ");
        int rating = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduceți comentariul dvs.: ");
        String comment = scanner.nextLine();

        // Adăugăm recenzia în baza de date
        Review review = new Review(rating, comment, client.getAge());
        reviewRepository.add(review);

        System.out.println("Recenzia a fost adăugată cu succes.");
    }

    public void showAllReviews() {
        List<Review> reviews = reviewRepository.getAll();

        if (reviews.isEmpty()) {
            System.out.println("Nu există recenzii disponibile.");
            return;
        }

        System.out.println("Lista de recenzii:");

        for (Review review : reviews) {
            System.out.println("ID: " + review.getId());
            System.out.println("Rating: " + review.getRating());
            System.out.println("Comentariu: " + review.getComment());
            System.out.println("Vârsta utilizatorului: " + review.getUserAge());
            System.out.println();
        }
    }
}