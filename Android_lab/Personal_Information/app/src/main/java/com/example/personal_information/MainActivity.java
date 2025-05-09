package com.example.personal_information;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText edtten, editCMND, editBosung;
    CheckBox chkdocbao, chkdocsach, chkcode;
    Button btnsend;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần trong layout
        edtten = findViewById(R.id.edtten);
        editCMND = findViewById(R.id.edtcmnd);
        editBosung = findViewById(R.id.edtbosung);

        chkdocbao = findViewById(R.id.chkdocbao);
        chkdocsach = findViewById(R.id.chkdocsach);
        chkcode = findViewById(R.id.chkcode);

        group = findViewById(R.id.idgruop);
        btnsend = findViewById(R.id.btnsend);

        // Xử lý khi nhấn nút "Gửi thông tin"
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });
    }

    // Hàm xử lý kiểm tra và hiển thị thông tin
    public void doShowInformation() {
        // Kiểm tra tên
        String ten = edtten.getText().toString().trim();
        if (ten.length() < 3) {
            edtten.requestFocus();
            edtten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra CMND
        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9 || !cmnd.matches("\\d{9}")) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 chữ số", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra bằng cấp
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        String bang = rad.getText().toString();

        // Kiểm tra sở thích
        String sothich = "";
        if (chkdocbao.isChecked()) sothich += "- " + chkdocbao.getText() + "\n";
        if (chkdocsach.isChecked()) sothich += "- " + chkdocsach.getText() + "\n";
        if (chkcode.isChecked()) sothich += "- " + chkcode.getText() + "\n";
        if (sothich.equals("")) {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_LONG).show();
            return;
        }

        // Thông tin bổ sung
        String bosung = editBosung.getText().toString();

        // Tạo và hiển thị hộp thoại AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        String msg = "Họ tên: " + ten + "\n";
        msg += "CMND: " + cmnd + "\n";
        msg += "Bằng cấp: " + bang + "\n";
        msg += "Sở thích:\n" + sothich;
        msg += "———————————\n";
        msg += "Thông tin bổ sung:\n" + bosung + "\n";
        msg += "———————————";

        builder.setMessage(msg);
        builder.create().show();
    }

    // Xử lý khi nhấn nút "Back"
    @Override
    public void onBackPressed() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Xác nhận thoát");
        b.setMessage("Bạn có chắc chắn muốn thoát không?");
        b.setIcon(android.R.drawable.ic_dialog_alert);

        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Thoát ứng dụng
            }
        });

        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Đóng dialog
            }
        });

        b.create().show();
    }
}
