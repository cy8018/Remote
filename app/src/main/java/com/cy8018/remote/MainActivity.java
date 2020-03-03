package com.cy8018.remote;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btnVolUp, btnVolDown, btnNext, btnPrev, btnPlayPause, btnMute, btnF5S, btnF30S, btnB5S, btnB30S;
    private TextView text;
    private EditText editTextIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.textView);
        editTextIP = (EditText) findViewById(R.id.editText);

        btnVolUp = (Button) findViewById(R.id.btnVolUp);
        btnVolDown = (Button) findViewById(R.id.btnVolDown);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnPlayPause = (Button) findViewById(R.id.btnPlayPause);
        btnMute = (Button) findViewById(R.id.btnMute);
        btnF5S = (Button) findViewById(R.id.btnF5S);
        btnF30S = (Button) findViewById(R.id.btnF30S);
        btnB5S = (Button) findViewById(R.id.btnB5S);
        btnB30S = (Button) findViewById(R.id.btnB30S);

        btnVolUp.setOnClickListener(this);
        btnVolDown.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnPlayPause.setOnClickListener(this);
        btnMute.setOnClickListener(this);
        btnF5S.setOnClickListener(this);
        btnF30S.setOnClickListener(this);
        btnB5S.setOnClickListener(this);
        btnB30S.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVolUp:
                text.setText("Vol+");
                Send("VolUp");
                break;
            case R.id.btnVolDown:
                text.setText("Vol-");
                Send("VolDown");
                break;
            case R.id.btnNext:
                text.setText("P+");
                Send("Next");
                break;
            case R.id.btnPrev:
                text.setText("P-");
                Send("Prev");
                break;
            case R.id.btnPlayPause:
                text.setText("Play/Pause");
                Send("PlayPause");
                break;
            case R.id.btnMute:
                text.setText("Mute");
                Send("Mute");
                break;
            case R.id.btnF5S:
                text.setText("F5S");
                Send("F5S");
                break;
            case R.id.btnF30S:
                text.setText("F30S");
                Send("F30S");
                break;
            case R.id.btnB5S:
                text.setText("B5S");
                Send("B5S");
                break;
            case R.id.btnB30S:
                text.setText("B30S");
                Send("B30S");
                break;
            default:
                break;
        }
    }

    private void Send(final String msg)
    {
        final String IP = "192.168.1." + editTextIP.getText().toString();
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Socket socket = new Socket(IP, 20000);
                    OutputStream os = socket.getOutputStream();
                    os.write(msg.getBytes());
                    os.flush();
                    socket.shutdownOutput();
                    os.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    //text.setText(e.getMessage());
                }
            }
        }.start();
    }
}
