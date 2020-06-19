package com.example.a1199;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

        ListView fFind_ListView1;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second);
            setTitle("파일별 용량(Second Activity)");

            initView();

            String ext = Environment.getExternalStorageState();
            if(ext.equals(Environment.MEDIA_MOUNTED)){
                findFolder();
            }else{
                Toast.makeText(this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
            }

            Button btnReturn1 = (Button) findViewById(R.id.btnReturn);

            btnReturn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                    setResult(RESULT_OK, outIntent);//결과값을 돌려줌
                    finish();
                }
            });
        }

        public void initView(){
            fFind_ListView1 = (ListView)findViewById(R.id.list1);
        }

        public void findFolder(){
            ArrayList<String> fName1 = new ArrayList<>(); //배열 fName1에 다 있음?
            File files1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");
            ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName1);
            if(files1.listFiles().length>0){
                for(File file : files1.listFiles()){
                    fName1.add(formatFileSize(file.length()));//formatFileSize 메소드 사용해서 long -> byte으로 바꿔줌
                }
            }
            files1 = null;
            fFind_ListView1.setAdapter(filelist);
        }

    public String formatFileSize(long bytes) {
        return android.text.format.Formatter.formatFileSize(this, bytes);
    }//long -> byte으로 바꿔주는 코드

}

