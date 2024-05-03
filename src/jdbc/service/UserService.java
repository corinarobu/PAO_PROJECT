package jdbc.service;

import jdbc.exceptions.InvalidDataException;
import jdbc.model.App_User;
import jdbc.model.Client;
import jdbc.model.Employee;
import jdbc.model.Review;

import jdbc.persistence.EmployeeRepository;
import jdbc.persistence.ClientRepository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private List<App_User> userList = new ArrayList<>();
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private Scanner scanner;
    private List<Client> clientList;
    private List<Employee> employeeList;

    private List<Review> reviewList = new ArrayList<>();



    public UserService(ClientRepository clientRepository, EmployeeRepository employeeRepository) throws InvalidDataException {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.scanner = new Scanner(System.in);
        this.clientList = clientRepository.getAll();
        this.employeeList = employeeRepository.getAll();
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


    public void addUser(App_User user) {
            userList.add(user);
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

        System.out.println("Introduceți id-ul userului:");
        int user_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Introduceți adresa clientului:");
        String address = scanner.nextLine();

        System.out.println("Introduceți id-ul clientului:");
        int client_id = Integer.parseInt(scanner.nextLine());

        App_User user = new Client(user_id, username, email, password, phoneNumber, age, client_id, address);


        addUser(user);
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

        System.out.println("Introduceți id-ul userului:");
        int user_id = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceți id-ul angajatului:");
        int employee_id = Integer.parseInt(scanner.nextLine());

        App_User user = new Employee(user_id, username, email, password, phoneNumber, age, employee_id, jobTitle
                , hiringDate,  salary,  dailyWorkHours);

        addUser(user);;
        System.out.println("Angajat adăugat cu succes.");
    }

    public void showUsers() {
        System.out.println("Users:");

        for (App_User user : userList) {
            if (user instanceof Client) {
                System.out.println("Client: " + user);
            } else if (user instanceof Employee) {
                System.out.println("Employee: " + user);
            }
        }
    }

    public void updateUserProfile() {
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        App_User currentUser = null;
        for (App_User user : userList) {
            if (user.getId() == userId) {
                currentUser = user;
                break;
            }
        }

        if (currentUser == null) {
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

        currentUser.setPassword(password);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setAge(age);

        System.out.println("User profile updated successfully.");
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

    public void deleteUser(App_User user) {
        userList.remove(user);
    }

    public void deleteClient() {
        System.out.print("Enter client ID: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        App_User userToDelete = null;
        for (App_User user : userList) {
            if (user.getId() == clientId) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete == null) {
            System.out.println("User with ID " + clientId + " not found.");
            return;
        }

        if (!(userToDelete instanceof Client)) {
            System.out.println("User with ID " + clientId + " is not a client.");
            return;
        }

        System.out.println("Are you sure you want to delete this client? (Y/N)");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {
            deleteUser(userToDelete);
            System.out.println("Client deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }


    public void deleteEmployee() {
        System.out.print("Enter employee ID: ");
        int employeeId = Integer.parseInt(scanner.nextLine());

        App_User userToDelete = null;
        for (App_User user : userList) {
            if (user.getId() == employeeId) {
                userToDelete = user;
                break;
            }
        }

        if (userToDelete == null) {
            System.out.println("User with ID " + employeeId + " not found.");
            return;
        }

        System.out.println("Are you sure you want to delete this user? (Y/N)");
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {
            deleteUser(userToDelete);
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("Deletion canceled.");
        }
    }




    public void leaveReview() {
        System.out.print("Introduceți ID-ul dvs. de client: ");
        int clientId = Integer.parseInt(scanner.nextLine());

        Client client = null;
        for (App_User user : userList) {
            if (user instanceof Client && user.getId() == clientId) {
                client = (Client) user;
                break;
            }
        }

        if (client == null) {
            System.out.println("Clientul cu ID-ul " + clientId + " nu există.");
            return;
        }

        System.out.print("Introduceți rating-ul (de la 1 la 5): ");
        int rating = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduceți comentariul dvs.: ");
        String comment = scanner.nextLine();

        Review review = new Review(rating, comment, client.getAge());

//        client.addReview(review);

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
