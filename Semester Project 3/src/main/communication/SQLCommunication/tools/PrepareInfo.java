package communication.SQLCommunication.tools;
/** Represents an info preparer
 * @author Michael P
 * PrepareInfo constructs the info for selecting,
 * inserting & deleting data in the database
 */
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
