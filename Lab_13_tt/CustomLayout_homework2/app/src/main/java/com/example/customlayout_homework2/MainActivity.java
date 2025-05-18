package com.example.customlayout_homework2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContacts;
    ArrayList<Contact> contactList;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContacts = findViewById(R.id.lvContacts);

        contactList = new ArrayList<>();
        contactList.add(new Contact("Nguyễn Văn Nam", "0932588634"));
        contactList.add(new Contact("Trần Văn Tú", "0932588635"));
        contactList.add(new Contact("Nguyễn Thị Lan", "0932588636"));
        contactList.add(new Contact("Nguyễn Kim Ngân", "0932588637"));

        adapter = new ContactAdapter(this, R.layout.layout_contact_item, contactList);
        lvContacts.setAdapter(adapter);
    }
}
