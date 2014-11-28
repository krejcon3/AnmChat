package cz.cvut.anmchat.app.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.LinkedList;

import cz.cvut.anmchat.app.R;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.message.Message;
import cz.cvut.anmchat.app.business.message.useCases.CreateMessageUC;
import cz.cvut.anmchat.app.business.message.useCases.GetMessagesUC;

public class RoomActivity extends Activity implements View.OnClickListener {

    private String myHash;
    private long roomId;
    private String roomName;
    protected LinkedList<Message> list;
    private MessageDecorator messageDecorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        this.myHash = intent.getStringExtra("myHash");
        this.roomName = intent.getStringExtra("roomName");
        this.roomId = intent.getLongExtra("roomId", -1);

        this.messageDecorator = new MessageDecorator(this.myHash, this.getApplicationContext());

        setTitle(getTitle() + " - " + this.roomName);

        if (myHash == null) {
            Toast.makeText(this.getApplicationContext(), "Sorry, you haven't hash id, please wait a few seconds.", Toast.LENGTH_SHORT).show();
            this.finish();
        }

        TextView input = (TextView)findViewById(R.id.input);
        input.setHint("Type message");

        Button send = (Button)findViewById(R.id.button);
        send.setText("Send");
        send.setOnClickListener(this);

        this.setList();

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                setList();
                h.postDelayed(this, 10 * 1000);
            }
        }, 10 * 1000);
    }

    private void setList() {
        try {
            this.list = new GetMessagesUC().getAll(this.roomId);
            this.list = this.messageDecorator.decorate(this.list);
            ListView msgList = (ListView) findViewById(R.id.list);
            MessageAdapter adapter = new MessageAdapter(this.list, getApplicationContext());
            msgList.setAdapter(adapter);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    private void createMessage(String message) {
        try {
            new CreateMessageUC().create(new Message(this.myHash, message), this.roomId);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                TextView tv = (TextView)findViewById(R.id.input);
                this.createMessage(tv.getText().toString());
                tv.setText("");
                this.setList();
                break;
            default:
                return;
        }
    }
}
