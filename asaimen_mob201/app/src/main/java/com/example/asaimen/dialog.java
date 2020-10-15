package com.example.asaimen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asaimen.Adapter.SpinnerAdapter;
import com.example.asaimen.Dao.LopDao;
import com.example.asaimen.Dao.StudentDao;
import com.example.asaimen.Model.Lop;
import com.example.asaimen.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class dialog  extends AppCompatActivity {
    EditText edtHoten;
    StudentDao studentDao;
    Spinner spinner;
    Button btnUpdate, btnCancel;
    LopDao lopDao;
    List<Lop> lopList;
    String malopt;
    String maloptv,tensv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);
        edtHoten=findViewById(R.id.tvdialog);
        spinner=findViewById(R.id.spiner_dialog);
        lopDao=new LopDao(dialog.this);
        lopList=new ArrayList<>();
        lopList=lopDao.getAllLop();

        SpinnerAdapter myAdapter=new SpinnerAdapter(dialog.this, lopList);
        spinner.setAdapter(myAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Lop loptx=lopList.get(i);
                malopt=loptx.getMaLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });



        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        maloptv = b.getString("malop");
        tensv = b.getString("hoTen");
        edtHoten.setText(tensv);

    }
    public void updateUser(View view) {
        Student n = new Student();
        n.setMaLop(malopt);
        n.setTenSV(edtHoten.getText().toString());

        int kq = studentDao.updateStudent(n);
        if(kq<=0)
        {
            Toast.makeText(getApplicationContext(),"Update that bai",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Update thanh cong",Toast.LENGTH_LONG).show();
        }

    }

}