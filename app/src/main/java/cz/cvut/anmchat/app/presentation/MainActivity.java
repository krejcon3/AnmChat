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
import cz.cvut.anmchat.app.business.GetOwnHashUC;
import cz.cvut.anmchat.app.business.room.Room;
import cz.cvut.anmchat.app.business.room.useCases.CreateRoomUC;
import cz.cvut.anmchat.app.business.room.useCases.GetRoomsUC;

public class MainActivity extends Activity implements View.OnClickListener {

    private String myHash;
    protected LinkedList<Room> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (myHash == null) {
            // get hash from server
            try {
                myHash = new GetOwnHashUC().get();
            } catch (BusinessException e) {
                Toast.makeText(this.getApplicationContext(), "Server downloading hash id, please wait a few seconds.", Toast.LENGTH_SHORT).show();
            }
        }

        TextView input = (TextView)findViewById(R.id.input);
        input.setHint("Name of new room");

        Button send = (Button)findViewById(R.id.button);
        send.setText("Create");
        send.setOnClickListener(this);

        setList();

        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                setList();
                h.postDelayed(this, 30 * 1000);
            }
        }, 30 * 1000);
    }

    private void setList() {
        new GetRoomsAsyncTask().execute(this.getApplicationContext());
    }

    private void startRoomActivity(int position, long id) {
        Room clicked = this.list.get(position);
        Intent intent = new Intent(this, RoomActivity.class);
        intent.putExtra("myHash", this.myHash);
        intent.putExtra("roomId", clicked.getId());
        intent.putExtra("roomName", clicked.getName());
        this.startActivity(intent);
    }

    private void createRoom(String name) {
        new CreateRoomAsyncTask().execute(new Room(name));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                TextView tv = (TextView)findViewById(R.id.input);
                this.createRoom(tv.getText().toString());
                tv.setText("");
                this.setList();
                break;
            default:
                Log.e("MSL", "Undefined button pressed.");
        }
    }

    private class GetRoomsAsyncTask extends AsyncTask<Context, Void, LinkedList<Room>> {

        @Override
        protected LinkedList<Room> doInBackground(Context... c) {
            GetRoomsUC uc = new GetRoomsUC();
            LinkedList<Room> list = new LinkedList<Room>();
            try {
                list = uc.getAll();
            } catch (BusinessException e) {
                Log.e("ERROR", e.getMessage());
            } finally {
                return list;
            }
        }

        @Override
        protected void onPostExecute(final LinkedList<Room> rooms) {
            super.onPostExecute(rooms);
            list = rooms;
            runOnUiThread(new Runnable() {
                public void run() {
                    ListView msgList = (ListView) findViewById(R.id.list);
                    RoomAdapter adapter = new RoomAdapter(rooms, getApplicationContext());
                    msgList.setAdapter(adapter);
                    msgList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            startRoomActivity(position, id);
                        }
                    });
                }
            });
        }
    }

    private class CreateRoomAsyncTask extends AsyncTask<Room, Void, Void> {
        @Override
        protected Void doInBackground(Room... r) {
            try {
                new CreateRoomUC().create(r[0]);
                Toast.makeText(getApplicationContext(), r[0].getName(), Toast.LENGTH_SHORT).show();
            } catch (BusinessException e) {
                Log.e("ERROR", e.getMessage());
            }
            return null;
        }
    }
}
