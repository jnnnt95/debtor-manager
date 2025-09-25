package model;

// This class is intended to provide performant access to client data
public class ClientData {
    private final int id;
    private final String nick;
    private final String name;
    private final String cpnumber;
    private final String area;
    private final String createdBy;

    public ClientData(
            int id,
            String nick,
            String name,
            String cpnumber,
            String area,
            String createdBy
    ) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.cpnumber = cpnumber;
        this.area = area;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public String getName() {
        return name;
    }

    public String getCpnumber() {
        return cpnumber;
    }

    public String getArea() {
        return area;
    }

    public String getCreatedBy() {
        return createdBy;
    }
}
