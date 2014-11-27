package cz.cvut.anmchat.app.business.message;

/**
 * Created by krejcir on 9.11.14.
 */
public class Message {

    private long id;
    private String author;
    private String message;
    private String date;
    private String user_hash;

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

    public Message(long id, String author, String message, String date) {
        this.id = id;
        this.author = author;
        this.message = message;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUser_hash() {
        return user_hash;
    }

    public void setUser_hash(String user_hash) {
        this.user_hash = user_hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
