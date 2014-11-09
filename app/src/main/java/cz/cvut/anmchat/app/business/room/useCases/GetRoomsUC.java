package cz.cvut.anmchat.app.business.room.useCases;

import java.util.LinkedList;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.room.Room;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetRoomsUC extends AbstractRESTUC {

    public LinkedList<Room> getAll() throws BusinessException {
        LinkedList<Room> rooms = new LinkedList<Room>();
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(i, "Room " + i));
        }
        return rooms;
    }
}
