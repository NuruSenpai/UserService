package org.example.userservice.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "users")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

@Column(unique = true)
private String email;

private int age;

@Column(name = "created_at")
private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}


    public User(String name, String email, int age ) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Имя: " + name + "\n" +
                "Email: " + email + "\n" +
                "Возраст: " + age + "\n" +
                "Создан: " + createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + "\n";
    }


}
