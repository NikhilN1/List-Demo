package com.yoska.listdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button  delButton, addButton;
    EditText editText;
    ArrayList<String> items;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        delButton = findViewById(R.id.buttonDelete);
        addButton = findViewById(R.id.addItem);
        editText = findViewById(R.id.item_text);

        items = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, items);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();

                int count = listView.getCount();

                for(int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        adapter.remove(items.get(item));
                        Toast.makeText(MainActivity.this,"Items Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                positionChecker.clear();
                adapter.notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(editText.getText().toString());
                editText.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);
    }
}