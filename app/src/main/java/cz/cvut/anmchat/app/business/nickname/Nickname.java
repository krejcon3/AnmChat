package cz.cvut.anmchat.app.business.nickname;

/**
 * Created by krejcir on 9.11.14.
 */
public class Nickname {

    private long id;
    private String nickname;
    private String hash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
