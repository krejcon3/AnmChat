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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        this.myHash = intent.getStringExtra("myHash");
        this.roomName = intent.getStringExtra("roomName");
        this.roomId = intent.getLongExtra("roomId", -1);

        setTitle(getTitle() + " - " + this.roomName);

        if (myHash == null) {
            // toast generate new hash
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
        new GetMessagesAsyncTask().execute(this.getApplicationContext());
    }

    private void createMessage(String message) {
        new CreateMessageAsyncTask().execute(new Message(this.myHash, message));
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
                Log.e("MSL", "Undefined button pressed.");
        }
    }

    private class GetMessagesAsyncTask extends AsyncTask<Context, Void, LinkedList<Message>> {

        @Override
        protected LinkedList<Message> doInBackground(Context... c) {
            GetMessagesUC uc = new GetMessagesUC();
            LinkedList<Message> list = new LinkedList<Message>();
            try {
                list = uc.getAll();
            } catch (BusinessException e) {
                Log.e("ERROR", e.getMessage());
            } finally {
                return list;
            }
        }

        @Override
        protected void onPostExecute(final LinkedList<Message> messages) {
            super.onPostExecute(messages);
            list = messages;
            runOnUiThread(new Runnable(){
                public void run(){
                    ListView msgList = (ListView) findViewById(R.id.list);
                    MessageAdapter adapter = new MessageAdapter(messages, getApplicationContext());
                    msgList.setAdapter(adapter);
                    msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        }
                    });
                }
            });
        }
    }

    private class CreateMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        @Override
        protected Void doInBackground(Message... m) {
            try {
                new CreateMessageUC().create(m[0]);
                Toast.makeText(getApplicationContext(), m[0].getMessage(), Toast.LENGTH_SHORT).show();
            } catch (BusinessException e) {
                Log.e("ERROR", e.getMessage());
            }
            return null;
        }
    }
}
