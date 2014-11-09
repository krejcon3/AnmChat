package cz.cvut.anmchat.app.business.room;

/**
 * Created by krejcir on 9.11.14.
 */
public class Room {

    private long id;
    private String name;

    public Room(String name) {
        this.name = name;
    }

    public Room(long id, String name) {
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
}
