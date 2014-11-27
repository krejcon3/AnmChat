package cz.cvut.anmchat.app.business.nickname.useCases;

import android.content.Context;

import java.util.HashMap;

import cz.cvut.anmchat.app.business.AbstractDatabaseUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.integration.IntegrationException;

/**
 * Created by krejcir on 9.11.14.
 */
public class CreateNicknameUC extends AbstractDatabaseUC {

    public CreateNicknameUC(Context context) {
        super(context);
    }

    public void create(Nickname nickname) throws BusinessException {
        try {
            this.helper.create(nickname);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
