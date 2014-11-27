package cz.cvut.anmchat.app.business.nickname.useCases;

import android.content.Context;

import java.util.HashMap;
import java.util.LinkedList;

import cz.cvut.anmchat.app.business.AbstractDatabaseUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.integration.IntegrationException;

/**
 * Created by krejcir on 9.11.14.
 */
public class FindNicknameUC extends AbstractDatabaseUC {

    public FindNicknameUC(Context context) {
        super(context);
    }

    public Nickname find(long id) throws BusinessException {
        try {
            return this.helper.find(id);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public Nickname find(String hash) throws BusinessException {
        try {
            return this.helper.find(hash);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public HashMap<String, Nickname> find() throws BusinessException {
        return this.helper.find();
    }
}
