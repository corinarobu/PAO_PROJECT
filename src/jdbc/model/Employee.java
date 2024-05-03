package jdbc.model;

import java.util.Date;
import java.lang.String;

public class Employee extends App_User {
    private int employee_id;
    private String jobTitle;
    private Date hiringDate;
    private int salary;
    private int dailyWorkHours;

    public Employee(int user_id, String username, String email, String password, String phoneNumber, int age, int employee_id, String jobTitle, Date hiringDate, int salary, int dailyWorkHours) {
        super(user_id, username, email, password, phoneNumber, age);
        this.jobTitle = jobTitle;
        this.employee_id = employee_id;
        this.hiringDate = hiringDate;
        this.salary = salary;
        this.dailyWorkHours = dailyWorkHours;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public void setUserDetails(String password, String phoneNumber, int age) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }


    public java.sql.Date getHiringDate() {
        return (java.sql.Date) hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }
}