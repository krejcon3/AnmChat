package cz.cvut.anmchat.app.business;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetOwnHashUC extends AbstractRESTUC {

    public String get() throws BusinessException {
        return "HASHCODE";
    }
}
