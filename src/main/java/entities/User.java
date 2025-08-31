package entities;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private int age;

    public User(String name, String email,int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return " Пользователь{" +
                " id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", email=" + email + '\'' +
                '}';
    }
}