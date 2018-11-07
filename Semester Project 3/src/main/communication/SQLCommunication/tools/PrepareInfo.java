package communication.SQLCommunication.tools;

public class PrepareInfo {

    private int place;
    private PrepareType type;
    private Object data;

    public PrepareInfo(int place, PrepareType type, Object data) {
        this.place = place;
        this.type = type;
        this.data = data;
    }

    public int getPlace() {
        return place;
    }

    public PrepareType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
