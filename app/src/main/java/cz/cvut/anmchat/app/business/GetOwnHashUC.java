package cz.cvut.anmchat.app.business;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import cz.cvut.anmchat.app.integration.GetClient;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetOwnHashUC extends AbstractRESTUC {

    public String get() throws BusinessException {
        GetClient client = new GetClient();
        try {
            String response = client.execute("http://via.kopriva.net/chat/user").get();
            Gson gson = new Gson();
            UserHash uh = gson.fromJson(response, UserHash.class);
            return uh.getUser_login();
        } catch (InterruptedException e) {
            throw new BusinessException(e.getMessage());
        } catch (ExecutionException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private class UserHash {
        private String user_login;

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }
    }
}
