package jdbc.model;

public abstract class App_User {
    static protected int idCounter = 0;
    protected  int user_id;
    protected final String username;
    protected final String email;
    protected String password;
    protected int age;
    protected String phoneNumber;

    public App_User(int user_id, String username, String email, String password, String phoneNumber, int age) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public App_User(App_User other) {
        this.user_id = other.user_id;
        this.username = other.username;
        this.email = other.email;
        this.password = other.password;
        this.phoneNumber = other.phoneNumber;
        this.age = other.age;
    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }





    public int getId() {
        return user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract String toString();

    public void setAge(int age) {
     this.age = age;
    }

    public void setUserDetails(String password, String phoneNumber, int age) {
        this.password = password;
        this.phoneNumber =phoneNumber;
        this.age = age;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
