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
    
    
}
