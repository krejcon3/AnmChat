package cz.cvut.anmchat.app.business.nickname.useCases;

import android.content.Context;

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

    public Nickname Nickname(long id) throws BusinessException {
        try {
            return this.helper.find(id);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public Nickname Nickname(String hash) throws BusinessException {
        try {
            return this.helper.find(hash);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public LinkedList<Nickname> Nickname() throws BusinessException {
        try {
            return this.helper.find();
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
