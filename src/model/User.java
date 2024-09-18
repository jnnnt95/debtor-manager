package model;

import model.enums.UserType;

public class User {
    
    private final int id;
    private final String username;
    private final String phone;
    private final String dateCreated;
    private final UserType type;
    private final String name;
    
    public User(int id,
            String username,
            String phone,
            String dateCreated,
            String type,
            String name) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.dateCreated = dateCreated;
        switch(type) {
            case "administrator":
                this.type = UserType.administrator;
                break;
            case "normal":
                this.type = UserType.normal;
                break;
            default:
                this.type = UserType.normal;
                break;

        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public UserType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
    public static class UserBuilder {
        private int id;
        private String username;
        private String phone;
        private String dateCreated;
        private String type;
        private String name;

        private boolean done;

        public UserBuilder() {}

        public User build() {
            if(done) {
                return new User(
                        id,
                        username,
                        phone,
                        dateCreated,
                        type,
                        name
                );
            } else {
                throw new RuntimeException("Builder no preparado para crear usuario.");
            }
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder withDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public UserBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public void setDone() {
            this.done = true;
        }
    }
}
