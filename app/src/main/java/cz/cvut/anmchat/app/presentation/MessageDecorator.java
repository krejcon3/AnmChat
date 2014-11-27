package cz.cvut.anmchat.app.presentation;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;

import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.message.Message;
import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.business.nickname.useCases.CreateNicknameUC;
import cz.cvut.anmchat.app.business.nickname.useCases.FindNicknameUC;
import cz.cvut.anmchat.app.business.nickname.useCases.GenerateNicknameUC;

/**
 * Created by krejcir on 25.11.14.
 */
public class MessageDecorator {

    private String myHash;
    private GenerateNicknameUC generateNicknameUC;
    private FindNicknameUC findNicknameUC;
    private CreateNicknameUC createNicknameUC;

    public MessageDecorator(String myHash, Context context) {
        this.myHash = myHash;
        this.generateNicknameUC = new GenerateNicknameUC(context);
        this.findNicknameUC = new FindNicknameUC(context);
        this.createNicknameUC = new CreateNicknameUC(context);

    }

    public LinkedList<Message> decorate(LinkedList<Message> messages) throws BusinessException {
        HashMap<String, Nickname> nicknames = this.findNicknameUC.find();
        Nickname nickname;
        for (Message msg : messages) {
            if (this.myHash.equals(msg.getUser_hash())) {
                msg.setAuthor("You");
            } else {
                if (!nicknames.containsKey(msg.getUser_hash())) {
                    String name = this.generateNicknameUC.generate();
                    nickname = new Nickname(name, msg.getUser_hash());
                    nicknames.put(msg.getUser_hash(), nickname);
                    this.createNicknameUC.create(nickname);
                }
                msg.setAuthor(nicknames.get(msg.getUser_hash()).getNickname());
            }
        }
        return messages;
    }
}
