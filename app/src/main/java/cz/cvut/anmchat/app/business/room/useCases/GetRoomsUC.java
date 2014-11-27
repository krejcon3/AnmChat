package cz.cvut.anmchat.app.business.room.useCases;


import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.room.Room;
import cz.cvut.anmchat.app.integration.GetClient;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetRoomsUC extends AbstractRESTUC {

    public LinkedList<Room> getAll() throws BusinessException {
        GetClient client = new GetClient();
        try {
            String response = client.execute("http://via.kopriva.net/chat/room").get();
            Gson gson = new Gson();
            Room[] rooms = gson.fromJson(response, Room[].class);
            LinkedList<Room> rs = new LinkedList<Room>();
            for (Room r : rooms) {
                rs.add(r);
            }
            return rs;
        } catch (InterruptedException e) {
            throw new BusinessException(e.getMessage());
        } catch (ExecutionException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
