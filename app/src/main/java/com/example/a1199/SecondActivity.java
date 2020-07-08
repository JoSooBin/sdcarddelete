package com.example.a1199;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

        ListView fFind_ListView1;
        Button delete2,btnReturn1;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.second);
            setTitle("파일별 용량(Second Activity)");
            delete2 = (Button) findViewById(R.id.delete);

            initView1();

            String ext = Environment.getExternalStorageState();
            if(ext.equals(Environment.MEDIA_MOUNTED)){
                findFolder1();
            }else{
                Toast.makeText(this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
            }

            btnReturn1 = (Button) findViewById(R.id.btnReturn);

            btnReturn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                    setResult(RESULT_OK, outIntent);//결과값을 돌려줌
                    finish();
                }
            });

            this.DClick();
        }


    public void initView1(){
        fFind_ListView1 = (ListView)findViewById(R.id.list1);
    }


    public void findFolder1(){ //각 파일들의 용량구하기
        ArrayList<String> fName1 = new ArrayList<>();
        File files1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures"); //경로에서 파일 찾기
        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName1); //fName1를 리스트레이아웃으로 반환
        if(files1.listFiles().length>0){
            for(File file : files1.listFiles()){
                fName1.add(formatFileSize(file.length()));//formatFileSize 메소드 사용해서 long -> byte으로 바꿔줌
            }
        }
        files1 = null;
        fFind_ListView1.setAdapter(filelist);
    }

    public String formatFileSize(long bytes) { //long -> byte으로 바꿔주는 코드
        return android.text.format.Formatter.formatFileSize(this, bytes);
    }


    public void DClick() { //삭제
        delete2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                initView1();

                String ext = Environment.getExternalStorageState();
                if(ext.equals(Environment.MEDIA_MOUNTED)){
                    DClick1();

                    Toast.makeText(SecondActivity.this, "중복파일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SecondActivity.this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void DClick1() { //삭제를 위한 빌드업
        ArrayList<String> list = new ArrayList<>();//리스트 list 생성
        List<String> fName1 = new ArrayList<>(); //리스트 fName1생성

        File files1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");//경로지정. //Pictures경로 하위에서 파일들 가져오기

        ArrayAdapter<String> filelist1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list); //filelist1에 list리스트를 ListView에 띄울것임을 선언
        if(files1.listFiles().length>0){
            for(File file : files1.listFiles()){
                fName1.add(formatFileSize(file.length()));//formatFileSize 메소드 사용해서 long -> byte으로 바꿔줌 //각 파일의 용량 찾아서 리스트 fName1에 저장

            }
        }

        // fName1에서 중복 항목을 빼고 list에 넣기위한 반복문
        for(int i=0; i<fName1.size(); i++){
            if(!list.contains(fName1.get(i))) {   // list에 포함되어있는지 아닌지 체크
                list.add(fName1.get(i)); // 해당 값이 없으면 넣기
            }
        }

        fFind_ListView1.setAdapter(filelist1);//filelist1을 보내기
    }
}

