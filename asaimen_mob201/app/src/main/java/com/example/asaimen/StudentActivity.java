package com.example.asaimen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.asaimen.Database.DatabaseHelper;

public class StudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }
    public void themLop(View view){
        Intent intent = new Intent(this,activity_add_layer.class);
        startActivity(intent);
    }
    public void danhSachLop(View view){
        Intent intent = new Intent(this,activity_list_class.class);
        startActivity(intent);
    }
    public void themSinhVien(View view){
        Intent intent = new Intent(this,activity_add_student.class);
        startActivity(intent);
    }

    public void XoaDL(View view) {
        DatabaseHelper dataHelper = new DatabaseHelper(StudentActivity.this);
        dataHelper.DeleteAll();
        Toast.makeText(StudentActivity.this,"Xóa tất cả dữ liệu thành công",Toast.LENGTH_LONG).show();
    }
}