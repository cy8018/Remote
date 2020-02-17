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

    private Button btnVolUp, btnVolDown, btnNext, btnPrev;
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

        btnVolUp.setOnClickListener(this);
        btnVolDown.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
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
