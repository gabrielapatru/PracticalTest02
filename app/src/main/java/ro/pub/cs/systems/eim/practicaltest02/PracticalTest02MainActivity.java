package ro.pub.cs.systems.eim.practicaltest02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, abilitati, tipuri;
    Button connect;
    private ServerClass serverThread = null;
    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = "1999";
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerClass(Integer.parseInt(serverPort));
            if (serverThread.getServerSocket() == null) {
                Log.e("[PracticalTest02]", "[MAIN ACTIVITY] Could not create server thread!");
                return;
            }
            serverThread.start();
        }
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);
        imageView = (ImageView) findViewById(R.id.image_view);
        name = (TextView) findViewById(R.id.pokemon_name);
        abilitati = (TextView) findViewById(R.id.abilitati);
        tipuri = (TextView) findViewById(R.id.tipuri_pokemon);
        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(connectButtonClickListener);


    }
    @Override
    protected void onDestroy() {
        Log.i("[PracticalTest02]", "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
    }
}