package com.example.menu_driven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView name,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(TextView) findViewById(R.id.countryName);
        desc=(TextView) findViewById(R.id.description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.example_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.item1) {
            Toast.makeText(this, "item1", Toast.LENGTH_LONG).show();
            name.setText("ITEM1");
            desc.setText(R.string.item1);
        }
        else if(id==R.id.item2)
            Toast.makeText(this,"item2",Toast.LENGTH_LONG).show();
        else if(id==R.id.item3)
            Toast.makeText(this,"item3",Toast.LENGTH_LONG).show();
        else if(id==R.id.subitem1)
            Toast.makeText(this,"subitem1",Toast.LENGTH_LONG).show();
        else if(id==R.id.subitem2)
            Toast.makeText(this,"subitem2",Toast.LENGTH_LONG).show();
        else
            return super.onOptionsItemSelected(item);
        return true;
    }
}