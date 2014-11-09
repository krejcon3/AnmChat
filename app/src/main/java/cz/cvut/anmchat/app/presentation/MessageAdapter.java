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

public class MessageAdapter extends ArrayAdapter<Message> {

    private LinkedList<Message> messages;
    private Context context;

    public MessageAdapter(LinkedList<Message> messages, Context context) {
        super(context, R.layout.message, messages);
        this.messages = messages;
        this.context = context;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.message, parent, false);
        }

        TextView author = (TextView)convertView.findViewById(R.id.author);
        author.setText(messages.get(position).getAuthor());

        TextView message = (TextView)convertView.findViewById(R.id.message);
        message.setText(messages.get(position).getMessage());

        return convertView;
    }
}
