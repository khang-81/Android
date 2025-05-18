package com.example.calendar_notes;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends Activity {
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    SharedPreferences sharedPreferences;
    String PREF_NAME = "MyWorkData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        arraywork = new ArrayList<>();
        loadData();

        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        lv.setAdapter(arrAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtdate.setText("Hôm Nay: " + sdf.format(new Date()));

        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Info missing")
                            .setMessage("Please enter all information of the work")
                            .setPositiveButton("Continue", null)
                            .show();
                } else {
                    String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();
                    arraywork.add(str);
                    arrAdapter.notifyDataSetChanged();
                    saveData();

                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            arraywork.remove(i);
            arrAdapter.notifyDataSetChanged();
            saveData();
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<>(arraywork);
        editor.putStringSet("works", set);
        editor.apply();
    }

    private void loadData() {
        Set<String> set = sharedPreferences.getStringSet("works", new HashSet<>());
        arraywork.addAll(set);
    }
}
