package com.example.yamldemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int xp = 0;
    private int level = 1;

    // 1. Private constructor so only the Builder can create the object
    private User(UserBuilder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.xp = builder.xp;
        this.level = builder.level;
    }

    // No-argument constructor required by JPA
    public User() {}

    // 2. Static inner Builder class
    public static class UserBuilder {
        private int id;
        private String username;
        private String password;
        private int xp = 0;    // Default value
        private int level = 1; // Default value

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder xp(int xp) {
            this.xp = xp;
            return this;
        }

        public UserBuilder level(int level) {
            this.level = level;
            return this;
        }

        // 3. The build method that returns the User instance
        public User build() {
            return new User(this);
        }
    }

    // 4. Static method to start the builder process
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
}