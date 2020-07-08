package com.example.a1199;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {
    ListView fFind_ListView, fFind_ListView1;
    Button read1, delete1, fileSize1;
    // TextView text1;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("중복파일삭제");

        read1 = (Button) findViewById(R.id.read);
        fileSize1 = (Button) findViewById(R.id.fileSize);
        delete1 = (Button) findViewById(R.id.delete);


        this.RClick();
        fileSize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //새창으로 용량 확인하기
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
        this.DClick();
    }


    public void RClick() { //read클릭
        read1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView();

                String ext = Environment.getExternalStorageState();
                if (ext.equals(Environment.MEDIA_MOUNTED)) {
                    findFolder();

                    Toast.makeText(MainActivity.this, "Pictures폴더의 파일들을 추출하였습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void initView() {
        fFind_ListView = (ListView) findViewById(R.id.list);
    }

    public void findFolder() { //경로읽기
        ArrayList<String> fName = new ArrayList<String>();
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures");
        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, simple_list_item_1, fName);
        if (files.listFiles().length > 0) {
            for (File file : files.listFiles()) {
                fName.add(file.getName()); //파일이름 추출
            }
        }
        files = null;
        fFind_ListView.setAdapter(filelist);
    }


    public void DClick() { //삭제
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initView1();

                String ext = Environment.getExternalStorageState();
                if (ext.equals(Environment.MEDIA_MOUNTED)) {
                    DClick1();

                    Toast.makeText(MainActivity.this, "중복파일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void DClick1() {
        ArrayList<String> fName2 = new ArrayList<String>();
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Podcasts");
        ArrayAdapter<String> filelist2 = new ArrayAdapter<String>(this, simple_list_item_1, fName2);
        if (files.listFiles().length > 0) {
            for (File file : files.listFiles()) {
                fName2.add(file.getName());
            }
        }
        files = null;
        fFind_ListView1.setAdapter(filelist2);
    }

    public void initView1() {
        fFind_ListView1 = (ListView) findViewById(R.id.list);
    }

}