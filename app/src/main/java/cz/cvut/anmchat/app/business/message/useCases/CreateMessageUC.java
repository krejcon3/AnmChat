package cz.cvut.anmchat.app.business.message.useCases;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.message.Message;
import cz.cvut.anmchat.app.integration.PostClient;

/**
 * Created by krejcir on 9.11.14.
 */
public class CreateMessageUC extends AbstractRESTUC {

    public void create(Message msg, long roomId) throws BusinessException {
        PostClient client = new PostClient();
        try {
            Gson gson = new Gson();
            String s = gson.toJson(new NewMessage(msg.getMessage(), msg.getAuthor()));
            Log.i("MESSAGES:", s);
            String response = client.execute("http://via.kopriva.net/chat/message/" + roomId, s).get();
        } catch (InterruptedException e) {
            throw new BusinessException(e.getMessage());
        } catch (ExecutionException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public class NewMessage {
        private String message;
        private String user_hash;

        public NewMessage(String message, String user_hash) {
            this.message = message;
            this.user_hash = user_hash;
        }

        public String getMessage() {
            return message;
        }

        public String getUser_hash() {
            return user_hash;
        }
    }
}
