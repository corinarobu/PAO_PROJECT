package service;

import model.Client;
import model.Employee;
import persistence.ClientRepository;
import persistence.EmployeeRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private Scanner scanner;

    public UserService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
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

        System.out.println("Introduceți data angajării (în milisecunde de la Epoch):");
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
}
