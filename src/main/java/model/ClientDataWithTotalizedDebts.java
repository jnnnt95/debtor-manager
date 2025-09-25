package model;

// This class is intended to provide performant access to client data with totalized debts
public class ClientDataWithTotalizedDebts extends ClientData {

    private final int totalizedDebts;
    private final int totalizedDefaultDebts;

    public ClientDataWithTotalizedDebts(
            int id,
            String nick,
            String name,
            String cpnumber,
            String area,
            String createdBy,
            int totalizedDebts,
            int totalizedDefaultDebts
    ) {
        super(id, nick, name, cpnumber, area, createdBy);
        this.totalizedDebts = totalizedDebts;
        this.totalizedDefaultDebts = totalizedDefaultDebts;
    }

    public int getTotalizedDebts() {
        return totalizedDebts;
    }

    public int getTotalizedDefaultDebts() {
        return totalizedDefaultDebts;
    }

    public boolean isDefaulter() {
        return totalizedDefaultDebts != 0;
    }
}
