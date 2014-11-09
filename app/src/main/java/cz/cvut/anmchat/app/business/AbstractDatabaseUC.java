package cz.cvut.anmchat.app.business;

import android.content.Context;

import cz.cvut.anmchat.app.integration.AnmChatSQLiteHelper;

/**
 * Created by krejcir on 9.11.14.
 */
public abstract class AbstractDatabaseUC {

    protected AnmChatSQLiteHelper helper;

    public AbstractDatabaseUC(Context context) {
        this.helper = new AnmChatSQLiteHelper(context);
    }
}
