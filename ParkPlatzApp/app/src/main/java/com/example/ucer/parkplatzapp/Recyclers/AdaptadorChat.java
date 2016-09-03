package com.example.ucer.parkplatzapp.Recyclers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ucer.Controlador.Message;
import com.example.ucer.parkplatzapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Ivan on 17/05/2016.
 */
public class AdaptadorChat extends BaseAdapter {
    public final Context messageContext;
    public final ArrayList<Message> messageList;

    public AdaptadorChat(Context context, ArrayList<Message> messages){
        messageList = messages;
        messageContext = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return messageList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MessageViewHolder holder;
        final AdaptadorChat adapt = this;
        // if there is not already a view created for an item in the Message list.

        if (convertView == null){
            LayoutInflater messageInflater = (LayoutInflater) messageContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // create a view out of our `message.xml` file
            convertView = messageInflater.inflate(R.layout.mensaje, null);

            // create a MessageViewHolder
            holder = new MessageViewHolder();

            // set the holder's properties to elements in `message.xml`
            holder.email_View = (TextView) convertView.findViewById(R.id.message_sender);
            holder.msg_View = (TextView) convertView.findViewById(R.id.message_body);
            holder.time_View = (TextView)convertView.findViewById(R.id.message_time);
            // assign the holder to the view we will return
            convertView.setTag(holder);
        } else {

            // otherwise fetch an already-created view holder
            holder = (MessageViewHolder) convertView.getTag();
        }

        // get the message from its position in the ArrayList
        Message message = (Message) getItem(position);

        // set the elements' contents
        holder.msg_View.setText(message.getMensaje());
        holder.email_View.setText(message.getCorreo());
        holder.time_View.setText(message.getTime());

        // set the listeners to views
        final View cnvrtV = convertView;

        convertView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(final View v) {
                final Message msgToDel;
                TextView txtEmail = (TextView)v.findViewById(R.id.message_sender);
                TextView txtMsg = (TextView)v.findViewById(R.id.message_body);
                TextView txtTime = (TextView)v.findViewById(R.id.message_time);
                String str_time = txtTime.getText().toString();
                String str_email = txtEmail.getText().toString();
                String str_txt = txtMsg.getText().toString();
                int index = 0;
                for(int i = 0; i<messageList.size(); i++){
                    if(messageList.get(i).getTime().equals(str_time) && messageList.get(i).getCorreo().equals(str_email) && messageList.get(i).getMensaje().equals(str_txt)){
                        index = i;
                        break;
                    }
                }

                AlertDialog.Builder confirm = new AlertDialog.Builder(messageContext);
                confirm.setTitle("Confirmación");
                confirm.setMessage("¿Desea eliminar este mensaje?");
                final int i = index;
                confirm.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapt.delete((Message)adapt.getItem(i));

                    }
                });
                confirm.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        return;
                    }
                });
                confirm.show();
                return true;
            }
        });

        return convertView;


    }
    public void add(Message message){
        messageList.add(message);
        notifyDataSetChanged();
    }
    public void delete(Message message){
        messageList.remove(message);
        notifyDataSetChanged();
    }
    //inner class
    private static class MessageViewHolder {
        public TextView email_View;
        public TextView msg_View;
        public TextView time_View;
    }
}
