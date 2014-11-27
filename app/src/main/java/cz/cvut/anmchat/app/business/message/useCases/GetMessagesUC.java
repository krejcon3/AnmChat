package cz.cvut.anmchat.app.business.message.useCases;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.message.Message;
import cz.cvut.anmchat.app.business.room.Room;
import cz.cvut.anmchat.app.integration.GetClient;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetMessagesUC extends AbstractRESTUC {

    public LinkedList<Message> getAll(long roomId) throws BusinessException {

        GetClient client = new GetClient();
        try {
            String response = client.execute("http://via.kopriva.net/chat/message/" + roomId).get();
            Gson gson = new Gson();
            Message[] messages = gson.fromJson(response, Message[].class);
            LinkedList<Message> ms = new LinkedList<Message>();
            for (Message m : messages) {
                ms.add(m);
            }
            return ms;
        } catch (InterruptedException e) {
            throw new BusinessException(e.getMessage());
        } catch (ExecutionException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
