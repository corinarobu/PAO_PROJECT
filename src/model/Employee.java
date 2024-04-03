package model;

import java.util.Date;
import java.lang.String;

public class Employee extends User {
    private String jobTitle;
    private Date hiringDate;
    private double salary;
    private int dailyWorkHours;

    public Employee(String username, String email, String password, String phoneNumber, int age, String jobTitle, Date hiringDate, double salary, int dailyWorkHours) {
        super(username, email, password, phoneNumber, age);
        this.jobTitle = jobTitle;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.dailyWorkHours = dailyWorkHours;
    }

    public Employee makeCopy() {
        return new RegularUser(this);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDailyWorkHours() {
        return dailyWorkHours;
    }

    public void setDailyWorkHours(int dailyWorkHours) {
        this.dailyWorkHours = dailyWorkHours;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id = " + getId() +
                "username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", hiringDate=" + hiringDate +
                ", salary=" + salary +
                ", dailyWorkHours=" + dailyWorkHours +
                '}';
    }
}