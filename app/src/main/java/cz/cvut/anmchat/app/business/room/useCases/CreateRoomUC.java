package cz.cvut.anmchat.app.business.room.useCases;

import android.util.Log;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.room.Room;
import cz.cvut.anmchat.app.integration.GetClient;
import cz.cvut.anmchat.app.integration.PostClient;

/**
 * Created by krejcir on 9.11.14.
 */
public class CreateRoomUC extends AbstractRESTUC {

    public void create(Room room) throws BusinessException {
        PostClient client = new PostClient();
        try {
            Gson gson = new Gson();
            String s = gson.toJson(room);
            String response = client.execute("http://via.kopriva.net/chat/room", s).get();
            Log.i("TEST - - - -", s);
        } catch (InterruptedException e) {
            throw new BusinessException(e.getMessage());
        } catch (ExecutionException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
