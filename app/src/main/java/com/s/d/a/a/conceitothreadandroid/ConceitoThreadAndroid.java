package com.s.d.a.a.conceitothreadandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConceitoThreadAndroid extends AppCompatActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String string = bundle.getString("myKey");
            TextView tvTelaPrincipal = findViewById(R.id.tvTelaPrincipal);
            tvTelaPrincipal.setText(string);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conceito_thread_android);
    }

    public void bOnClick(View v){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();
                SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
                String dateString = dateformat.format(new Date());
                bundle.putString("myKey", dateString);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        };
        Thread thrActivityPrincipal = new Thread(runnable);
        thrActivityPrincipal.start();
    }

}
