package cz.cvut.anmchat.app.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import cz.cvut.anmchat.app.R;
import cz.cvut.anmchat.app.business.message.Message;
import cz.cvut.anmchat.app.business.room.Room;

public class RoomAdapter extends ArrayAdapter<Room> {

    private LinkedList<Room> rooms;
    private Context context;

    public RoomAdapter(LinkedList<Room> messages, Context context) {
        super(context, R.layout.message, messages);
        this.rooms = messages;
        this.context = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.room, parent, false);
        }

        TextView title = (TextView)convertView.findViewById(R.id.title);
        title.setText(rooms.get(position).getName());

        return convertView;
    }
}
