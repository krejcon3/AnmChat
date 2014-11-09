package cz.cvut.anmchat.app.business.nickname.useCases;

import android.content.Context;

import cz.cvut.anmchat.app.integration.AnmChatSQLiteHelper;

/**
 * Created by krejcir on 9.11.14.
 */
public abstract class AbstractUC {

    AnmChatSQLiteHelper helper;

    public AbstractUC (Context context) {
        this.helper = new AnmChatSQLiteHelper(context);
    }
}
