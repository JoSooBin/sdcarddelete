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
import java.util.Collections;
import java.util.List;

import static android.R.*;
import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {
    ListView fFind_ListView, fFind_ListView1;
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
        this.DClick();
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

    public void DClick() { //삭제
        delete1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                initView1();

                String ext = Environment.getExternalStorageState();
                if(ext.equals(Environment.MEDIA_MOUNTED)){
                    DClick1();

                    Toast.makeText(MainActivity.this, "중복파일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "SDcard가 확인되지않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void DClick1() { //삭제를 위한 빌드업
                //ArrayList<String> list = new ArrayList<>();//리스트 list 생성. 커밋되는거야?
                File list = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");//경로지정. //Pictures경로 하위에서 파일들 가져오기
                List<String> fName1 = new ArrayList<>(); //리스트 fName1생성
                List<String> fName2 = new ArrayList<>(); //리스트 fName1생성

                File files1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");//경로지정. //Pictures경로 하위에서 파일들 가져오기

                ArrayAdapter<String> filelist1 = new ArrayAdapter<String>(this, simple_list_item_1,fName2); //filelist1에 list리스트를 ListView에 띄울것임을 선언
                if(files1.listFiles().length>0){
                    for(File file : files1.listFiles()){
                        fName1.add(formatFileSize(file.length()));//formatFileSize 메소드 사용해서 long -> byte으로 바꿔줌 //각 파일의 용량 찾아서 리스트 fName1에 저장

                        //list.add(file.length());
                    }
                }

                for(int i=0; i<files1.listFiles().length; i++){ // fName1에서 중복 항목을 빼고 list에 넣기위한 반복문
                    if(!fName2.contains(fName1.get(i))) {   // list에 포함되어있는지 아닌지 체크
                        fName2.add(fName1.get(i)); // 해당 값이 없으면 넣기
                    }
                }//파일 이름이 아니라 크기표기


                fFind_ListView1.setAdapter(filelist1);//filelist1을 보내기

    }

    public void initView1(){
        fFind_ListView1 = (ListView)findViewById(R.id.list);
    }


    public void initView(){
        fFind_ListView = (ListView)findViewById(R.id.list);
    }

    public void findFolder(){
        ArrayList<String> fName = new ArrayList<String>();
        File files = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");
        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, simple_list_item_1,fName);
        if(files.listFiles().length>0){
            for(File file : files.listFiles()){
                fName.add(file.getName());
            }
        }
        files = null;
        fFind_ListView.setAdapter(filelist);
    }

//    public void findFolder1(){ //용량구하기
//        ArrayList<String> fName1 = new ArrayList<>(); //배열 fName1에 다 있음?
//        File files1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures");
//        ArrayAdapter<String> filelist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fName1);
//        if(files1.listFiles().length>0){
//            for(File file : files1.listFiles()){
//                fName1.add(formatFileSize(file.length()));//formatFileSize 메소드 사용해서 long -> byte으로 바꿔줌
//            }
//        }
//        files1 = null;
//    }

    public String formatFileSize(long bytes) {
        return android.text.format.Formatter.formatFileSize(this, bytes);
    }//long -> byte으로 바꿔주는 코드

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