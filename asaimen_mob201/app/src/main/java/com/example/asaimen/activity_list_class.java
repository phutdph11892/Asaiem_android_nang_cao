package com.example.asaimen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asaimen.Adapter.LopAdapter;
import com.example.asaimen.Dao.LopDao;
import com.example.asaimen.Model.Lop;

import java.util.ArrayList;
import java.util.List;

public class activity_list_class extends AppCompatActivity {
    ListView listView;
    LopAdapter adapter = null;
    public static List<Lop> lopList=new ArrayList<>();
    EditText editText;
    LopDao lopDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        listView = findViewById(R.id.ltvListClass);
        final LopDao lopDao= new LopDao(activity_list_class.this);
        lopList  =lopDao.getAllLop();
        adapter = new LopAdapter(activity_list_class.this,lopList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, final int i, long l) {
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.dialog_sualop, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_list_class.this);
                builder.setTitle("Sữa Lớp");
                builder.setView(layout);
                builder.setCancelable(false);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editText = layout.findViewById(R.id.txt_suatenlop);
                   LopDao lopDao = new LopDao(activity_list_class.this);
                        Lop lop = lopList.get(i);
                        lop.setTenLop(editText.getText().toString());
                        lopDao.UpdateLop(lop);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(activity_list_class.this, "Update thành công", Toast.LENGTH_LONG).show();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}