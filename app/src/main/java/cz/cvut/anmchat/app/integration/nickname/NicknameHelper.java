package cz.cvut.anmchat.app.integration.nickname;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

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

    private SQLiteDatabase readableDatabase;
    private SQLiteDatabase writeableDatabase;

    public NicknameHelper(SQLiteDatabase readableDatabase, SQLiteDatabase writeableDatabase) {
        this.readableDatabase = readableDatabase;
        this.writeableDatabase = writeableDatabase;
    }

    public void create(Nickname n) throws IntegrationException {
        long id = this.writeableDatabase.insert(TABLE_NAME, null, this.toValues(n));
        if (id == -1) {
            throw new IntegrationException("Nickname can't be crated.");
        }
    }

    public Nickname find(long id) throws IntegrationException {
        Cursor c = this.readableDatabase.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_HASH},
                "id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null
        );
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            return toObject(c);
        }
        throw new IntegrationException("Nickname not found.");
    }
    public Nickname find(String hash) throws IntegrationException {
        Cursor c = this.readableDatabase.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_HASH},
                "hash = ?",
                new String[] {hash},
                null,
                null,
                null,
                null
        );
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            return toObject(c);
        }
        throw new IntegrationException("Nickname not found.");
    }
    public HashMap<String, Nickname> find() {
        HashMap<String, Nickname> map = new HashMap<String, Nickname>();
        Cursor c = this.readableDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        Nickname nickname;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
               nickname = this.toObject(c);
               map.put(nickname.getHash(), nickname);
            } while (c.moveToNext());
        }
        return map;
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
