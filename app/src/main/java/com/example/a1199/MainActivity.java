package com.example.a1199;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView fFind_ListView;
    Button read1,delete1,fileSize1;
   // TextView text1;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("중복파일삭제");

        read1 = (Button) findViewById(R.id.read);
        fileSize1 = (Button) findViewById(R.id.fileSize);
        delete1 = (Button) findViewById(R.id.delete);
        //text1= (TextView)findViewById(R.id.text);


        this.RClick();
        fileSize1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                                           startActivity(intent);
                                       }
                                   });
        //this.desc();
        //this.DClick();
    }


    public void RClick() {
            read1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view)
                {
                    initView();

                    String ext = Environment.getExternalStorageState();
                    if(ext.equals(Environment.MEDIA_MOUNTED)){
                        findFolder();

                        Toast.makeText(MainActivity.this, "Download폴더의 파일들을 추출하였습니다", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

//    public void DClick() { //삭제
//        read1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view)
//            {
//
//            }
//        });
//
//    }


    public void initView(){
        fFind_ListView = (ListView)findViewById(R.id.list);
    }

    public void findFolder(){
        ArrayList<String> fName = new ArrayList<String>();
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");
        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName);
        if(files.listFiles().length>0){
            for(File file : files.listFiles()){
                fName.add(file.getName());
            }
        }
        files = null;
        fFind_ListView.setAdapter(filelist);
    }


//    public void desc() { //내림차순
//        filesize();
//        desc1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Collections.sort(strFileSize);
//                Collections.reserve(strFileSize);
//            }
//        }
//    }


//    public void filesize() { //파일 사이즈 구하기
//        String strFileSize;
//
//        File oFile = new File("/sdcard/Pictures");
//
//        if (oFile.exists() )
//        {
//            long lFileSize = oFile.length();
//            strFileSize = Long.toString(lFileSize) + " bytes";
//        }
//        else
//        {
//            strFileSize = "파일없음";
//        }
//    }



}