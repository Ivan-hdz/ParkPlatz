package com.example.ucer.parkplatzapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ucer.Controlador.Message;
import com.example.ucer.WebService.WebService;
import com.example.ucer.parkplatzapp.Recyclers.AdaptadorChat;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Chat extends AppCompatActivity {

    AdaptadorChat messageAdapter;
    String mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        messageAdapter = new AdaptadorChat(this, new ArrayList<Message>());
        final ListView messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mensajeView = new AlertDialog.Builder(Chat.this);
                mensajeView.setTitle("Enviar mensaje.");
                mensajeView.setMessage("Escriba un mensaje:");

                final EditText txt_msg = new EditText(Chat.this);
                LinearLayout.LayoutParams lytParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                txt_msg.setLayoutParams(lytParams);
                mensajeView.setView(txt_msg);

                mensajeView.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mensaje = txt_msg.getText().toString();
                        SharedPreferences preferencias = Chat.this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        String correo = preferencias.getString("correo", "");
                        sendMensaje(mensaje, correo);
                    }

                    private void sendMensaje(String msg, String email) {
                        if (msg.equals("")) {
                            return;
                        }
                        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        Calendar calendario = Calendar.getInstance();
                        RequestParams params = new RequestParams();
                        params.put("txt", msg);
                        params.put("email", email);
                        params.put("time", dateFormat.format(calendario.getTime()));

                        AsyncHttpClient client = new AsyncHttpClient();

                        client.post("http://"+ Main_Window.IP+"/Parkplatz-web/ServletChatPusher", params, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txt_msg.setText("");
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Mensaje enviado :D",
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Algo salio mal :(",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        });
                    }
                });
                mensajeView.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                mensajeView.show();
            }
        });

        // initialize Pusher
        Pusher pusher = new Pusher("dbb69d4a492fcb09f89d");


        // subscribe to our "messages" channel
        Channel channel = pusher.subscribe("community_channel");

        // listen for the "new_message" event
        channel.bind("new_message", new SubscriptionEventListener() {

            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Message message = gson.fromJson(data, Message.class);
                        messageAdapter.add(message);
                        //System.out.println(data);
                        // have the ListView scroll down to the new message
                        messagesView.setSelection(messageAdapter.getCount() - 1);

                    }
                });

            }
        });

        // connect to the Pusher API
        pusher.connect();
    }
}
