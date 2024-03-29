package cz.cvut.anmchat.app.integration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;

import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.integration.nickname.NicknameHelper;

/**
 * Created by krejcir on 9.11.14.
 */
public class AnmChatSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "AnmChat";

    private NicknameHelper nicknameHelper;

    public AnmChatSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        nicknameHelper = new NicknameHelper(this.getReadableDatabase(), this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NicknameHelper.TABLE_DEFINITON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(NicknameHelper.TABLE_DROP);
            this.onCreate(db);
        }
    }

    public void create(Nickname n) throws IntegrationException {
        this.nicknameHelper.create(n);
    }

    public Nickname find(long id) throws IntegrationException {
        return this.nicknameHelper.find(id);
    }
    public Nickname find(String hash) throws IntegrationException {
        return this.nicknameHelper.find(hash);
    }
    public HashMap<String, Nickname> find() {
        return this.nicknameHelper.find();
    }
}
