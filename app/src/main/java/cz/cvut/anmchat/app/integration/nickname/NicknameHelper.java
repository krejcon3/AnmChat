package cz.cvut.anmchat.app.integration.nickname;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.integration.IntegrationException;

/**
 * Created by krejcir on 9.11.14.
 */
public class NicknameHelper {

    public static final String TABLE_NAME = "nicknames";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_HASH = "hash";

    public static final String TABLE_DEFINITON = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " VARCHAR, " + KEY_HASH + " VARCHAR)";
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS nicknames";

    private SQLiteDatabase db;

    public NicknameHelper(SQLiteDatabase db) {
        this.db = db;
    }

    public void create(Nickname n) throws IntegrationException {
        long id = db.insert(TABLE_NAME, null, this.toValues(n));
        if (id == -1) {
            throw new IntegrationException("Nickname can't be crated.");
        }
    }

    public Nickname find(long id) throws IntegrationException {
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_HASH},
                "id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if (c != null) {
            c.moveToFirst();
            return toObject(c);
        }
        throw new IntegrationException("Nickname not found.");
    }
    public Nickname find(String hash) throws IntegrationException {
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_HASH},
                "hash = ?",
                new String[] {hash},
                null,
                null,
                null,
                null
        );
        if (c != null) {
            c.moveToFirst();
            return toObject(c);
        }
        throw new IntegrationException("Nickname not found.");
    }
    public LinkedList<Nickname> find() throws IntegrationException {
        LinkedList<Nickname> list = new LinkedList<Nickname>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (c != null) {
            c.moveToFirst();
            do {
               list.add(this.toObject(c));
            } while (c.moveToNext());
        }
        throw new IntegrationException("Nickname not found.");
    }

    private Nickname toObject(Cursor cursor) {
        Nickname n = new Nickname();
        n.setId(Long.parseLong(cursor.getString(0)));
        n.setNickname(cursor.getString(1));
        n.setHash(cursor.getString(2));
        return n;
    }

    private ContentValues toValues(Nickname n) {
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, n.getNickname());
        v.put(KEY_HASH, n.getHash());
        return v;
    }
}
