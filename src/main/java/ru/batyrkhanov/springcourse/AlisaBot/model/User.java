package ru.batyrkhanov.springcourse.AlisaBot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "alisausers")
public class User {

    @Id
    @Column(name = "id")
    long id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "owner")
    private List<Message> messages;

    public User() {}

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
