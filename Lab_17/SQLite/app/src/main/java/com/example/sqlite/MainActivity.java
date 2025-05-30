package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String DB_PATH_SUFFIX = "/databases/";
    final String DATABASE_NAME = "qlsach.db";
    SQLiteDatabase database = null;

    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Copy database từ assets
        copyDatabaseFromAssets();

        // 2. Kiểm tra và mở database
        openDatabase();

        // 3. Kiểm tra kết nối database trước khi thao tác
        if (isDatabaseConnected()) {
            Toast.makeText(this, "Kết nối database thành công", Toast.LENGTH_SHORT).show();

            // 4. Kiểm tra bảng tồn tại
            if(checkTableExists("tbsach")) {
                // 5. Load dữ liệu nếu bảng tồn tại
                loadDataFromTable();
            } else {
                Toast.makeText(this, "Bảng tbsach không tồn tại", Toast.LENGTH_LONG).show();
                Log.e("Database", "Bảng tbsach không tồn tại trong database");
            }
        } else {
            Toast.makeText(this, "Lỗi kết nối database", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Kiểm tra kết nối database có thành công không
     * @return true nếu kết nối thành công, false nếu có lỗi
     */
    private boolean isDatabaseConnected() {
        try {
            if (database == null || !database.isOpen()) {
                Log.e("Database", "Database chưa được mở");
                return false;
            }

            // Thực hiện truy vấn đơn giản để kiểm tra kết nối
            Cursor cursor = database.rawQuery("SELECT 1", null);
            if (cursor != null) {
                cursor.close();
                Log.d("Database", "Kiểm tra kết nối database thành công");
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e("Database", "Lỗi kiểm tra kết nối database: " + e.getMessage());
            return false;
        }
    }

    /**
     * Copy database từ thư mục assets vào thư mục ứng dụng
     */
    private void copyDatabaseFromAssets() {
        try {
            // Đường dẫn đầy đủ tới file database
            String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
            File dbFile = new File(dbPath);

            // Log đường dẫn database để debug
            Log.d("Database", "Đường dẫn database: " + dbPath);

            // Nếu file chưa tồn tại hoặc cần copy lại
            if (!dbFile.exists()) {
                // Tạo thư mục databases nếu chưa có
                File dbDir = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
                if (!dbDir.exists()) {
                    if (!dbDir.mkdirs()) {
                        Log.e("Database", "Không thể tạo thư mục database");
                        return;
                    }
                }

                // Kiểm tra file có tồn tại trong assets không
                try {
                    InputStream is = getAssets().open(DATABASE_NAME);
                    OutputStream os = new FileOutputStream(dbPath);

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }

                    os.flush();
                    os.close();
                    is.close();

                    Log.d("Database", "Đã copy database từ assets thành công");
                    Log.d("Database", "Kích thước file: " + dbFile.length() + " bytes");
                } catch (IOException e) {
                    Log.e("Database", "File không tồn tại trong assets: " + DATABASE_NAME);
                    throw e;
                }
            } else {
                Log.d("Database", "Database đã tồn tại, không cần copy");
            }
        } catch (IOException e) {
            Log.e("Database", "Lỗi copy database từ assets", e);
            Toast.makeText(this, "Lỗi copy database: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Mở kết nối đến database
     */
    private void openDatabase() {
        try {
            String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
            database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

            // Kiểm tra version database để xác nhận mở thành công
            int version = database.getVersion();
            Log.d("Database", "Mở database thành công tại: " + dbPath);
            Log.d("Database", "Database version: " + version);

            // Log danh sách các bảng có trong database
            Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            Log.d("Database", "Tổng số bảng: " + cursor.getCount());
            while (cursor.moveToNext()) {
                Log.d("Database", "Bảng: " + cursor.getString(0));
            }
            cursor.close();

        } catch (Exception e) {
            Log.e("Database", "Lỗi mở database", e);
            Toast.makeText(this, "Lỗi mở database: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Kiểm tra bảng có tồn tại trong database không
     * @param tableName tên bảng cần kiểm tra
     * @return true nếu bảng tồn tại, false nếu không
     */
    private boolean checkTableExists(String tableName) {
        if (database == null || !database.isOpen()) {
            Log.e("Database", "Database chưa được mở khi kiểm tra bảng");
            return false;
        }

        Cursor cursor = null;
        try {
            cursor = database.rawQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name=?",
                    new String[]{tableName});

            boolean exists = (cursor.getCount() > 0);
            Log.d("Database", "Kiểm tra bảng " + tableName + ": " + (exists ? "TỒN TẠI" : "KHÔNG TỒN TẠI"));
            return exists;
        } catch (Exception e) {
            Log.e("Database", "Lỗi kiểm tra bảng " + tableName, e);
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * Đọc dữ liệu từ bảng và hiển thị lên ListView
     */
    private void loadDataFromTable() {
        mylist = new ArrayList<>();
        lv = findViewById(R.id.lv);
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT * FROM tbsach", null);
            Log.d("Database", "Số bản ghi trong bảng tbsach: " + cursor.getCount());

            if (cursor.moveToFirst()) {
                do {
                    // Điều chỉnh theo cấu trúc thực tế của bảng
                    String masach = cursor.getString(cursor.getColumnIndexOrThrow("masach"));
                    String tensach = cursor.getString(cursor.getColumnIndexOrThrow("tensach"));
                    int namxb = cursor.getInt(cursor.getColumnIndexOrThrow("namxb"));

                    String item = masach + " - " + tensach + " - " + namxb;
                    mylist.add(item);
                    Log.d("Database", "Đã thêm: " + item);
                } while (cursor.moveToNext());

                myadapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Log.e("Database", "Lỗi đọc dữ liệu từ bảng", e);
            Toast.makeText(this, "Lỗi đọc dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database != null && database.isOpen()) {
            database.close();
            Log.d("Database", "Đã đóng kết nối database");
        }
    }
}