package com.example.karaoke;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME = "arirang.db"; // Đã đổi tên file database

    EditText edttim;
    ListView lv1, lv2, lv3;
    ArrayList<Item> list1, list2, list3;
    myarrayAdapter myarray1, myarray2, myarray3;
    TabHost tab;
    ImageButton btnxoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processCopy(); // Copy CSDL từ assets
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null); // Sử dụng biến DATABASE_NAME

        addControl();
        addTim();
        addEvents();
    }

    private void addControl() {
        btnxoa = findViewById(R.id.btnxoa);
        tab = findViewById(R.id.tabhost);
        tab.setup();

        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.search));
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.list));
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.favourite));
        tab.addTab(tab3);

        edttim = findViewById(R.id.edttim);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        myarray1 = new myarrayAdapter(this, R.layout.listitem, list1);
        myarray2 = new myarrayAdapter(this, R.layout.listitem, list2);
        myarray3 = new myarrayAdapter(this, R.layout.listitem, list3);

        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    private void addEvents() {
        tab.setOnTabChangedListener(tabId -> {
            if (tabId.equalsIgnoreCase("t2")) {
                addDanhsach();
            }
            if (tabId.equalsIgnoreCase("t3")) {
                addYeuthich();
            }
        });

        btnxoa.setOnClickListener(v -> edttim.setText(""));
    }

    private void addYeuthich() {
        myarray3.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE YEUTHICH = 1", null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            list3.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
            c.moveToNext();
        }
        c.close();
        myarray3.notifyDataSetChanged();
    }

    private void addDanhsach() {
        myarray2.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList", null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            list2.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
            c.moveToNext();
        }
        c.close();
        myarray2.notifyDataSetChanged();
    }

    private void addTim() {
        edttim.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getdata();
            }

            private void getdata() {
                String dulieunhap = edttim.getText().toString();
                myarray1.clear();

                if (!dulieunhap.equals("")) {
                    Cursor c = database.rawQuery(
                            "SELECT * FROM ArirangSongList WHERE TENBH1 LIKE '%"+dulieunhap+"%' OR MABH LIKE '%"+dulieunhap+"%'", null);
                    c.moveToFirst();

                    while(!c.isAfterLast()) {
                        list1.add(new Item(c.getString(1), c.getString(2), c.getInt(6)));
                        c.moveToNext();
                    }
                    c.close();
                }
                myarray1.notifyDataSetChanged();
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying database from assets successful", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Error copying database: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = getAssets().open(DATABASE_NAME);
        String outFileName = getDatabasePath();

        File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists()) f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;

        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}