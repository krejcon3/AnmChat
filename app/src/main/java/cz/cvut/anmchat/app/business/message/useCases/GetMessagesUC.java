package cz.cvut.anmchat.app.business.message.useCases;

import java.util.Date;
import java.util.LinkedList;

import cz.cvut.anmchat.app.business.AbstractRESTUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.message.Message;

/**
 * Created by krejcir on 9.11.14.
 */
public class GetMessagesUC extends AbstractRESTUC {

    public LinkedList<Message> getAll() throws BusinessException {

        LinkedList<Message> rooms = new LinkedList<Message>();

        for (int i = 0; i < 10; i++) {
            rooms.add(new Message(i, "HASH " + i, i + ": " + new Date().toString(), "DATE"));
        }
        return rooms;
    }
}
