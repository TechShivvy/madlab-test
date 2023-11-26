package com.example.summa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button,settings;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HomePage");
    }

    public void disable(View view) {
//        view.setEnabled(false);
//        Button button = (Button) view;
//        button.setText("Disabled");

        View myView = findViewById(R.id.button);
        myView.setEnabled(false);
        Button button = (Button) myView;
        button.setText("Disabled");

        Log.d("shivi","button disabled");
    }
    public void handleClick(View view) {
        EditText t = findViewById(R.id.input);
        String ip = t.getText().toString();
        ((TextView)findViewById(R.id.textView)).setText(ip);
        Log.d("info",ip);
        Toast.makeText(this,ip,Toast.LENGTH_LONG).show();
    }

    public void launchSettings(View view) {
        Intent intent = new Intent(this,SettingsActivity.class);
        intent.putExtra("name",((EditText)findViewById(R.id.input)).getText().toString());
        startActivity(intent);
    }
}