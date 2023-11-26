package com.example.sd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText readFileName,writeFileName,input;
    private TextView output;
    private Button readButton,writeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readFileName=findViewById(R.id.readFileName);
        output = findViewById(R.id.readDisplay);
        readButton = findViewById(R.id.readButton);

        writeFileName=findViewById(R.id.writeFileName);
        input = findViewById(R.id.writeDisplay);
        writeButton = findViewById(R.id.writeButton);

        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File myFile=new File(getExternalFilesDir("/"),readFileName.getText().toString());
                    output.setText(readFile(myFile));
                    showToast("Read Success!");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    showToast("Error reading file");
                }
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File myFile = new File(getExternalFilesDir("/"),writeFileName.getText().toString());
                    writeFile(myFile,input.getText().toString());
                    showToast("Write Success!");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    showToast("Error writing file");
                }
            }
        });

    }

    private void writeFile(File file, String data) throws IOException {
        try(OutputStream writer = new FileOutputStream(file)){
            writer.write(data.getBytes());
        }
    }

    private String readFile(File file) throws IOException {
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream reader = new FileInputStream(file)) {
            reader.read(bytes);
        }
        String data = new String(bytes);
        return data;
    }

    private void showToast(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}