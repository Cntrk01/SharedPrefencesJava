package com.example.verisaklama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    TextView textView;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editTextNumber);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        //1.Paket ismini veriyoruz
        //2.Burada modlar var biz private olanı kullanıcaz yanlızca burdan ulaşılsın diye
       sh=this.getSharedPreferences("com.example.verisaklama", Context.MODE_PRIVATE);

       //5)burada 4 de kayıt edilen değeri çağıracağız.Yoksa def degeri vericek zaten
        int storageAge=sh.getInt("storedAge",0);
        if(storageAge==0){
            textView.setText("Your Age : ");
        }else{
            //kayıtlı değeri de set ettim.
            textView.setText("Your Age : "+storageAge);
        }
    }
    //3)onClick methoduna save ismini verdim ordan eriştik
    public void save(View view){
        if(!editText.getText().toString().matches("")){
            int age=Integer.parseInt(editText.getText().toString());
            textView.setText("Your Age : "+age);

            //4)shared çağırıp verimizi kayıt edicez.istersek farklı veri tipleride kayıt edeblrz string long gibi
            //birtane tutulacak isim ve degeri verdik.
            sh.edit().putInt("storedAge",age).apply();
        }
    }
    //6)Verilen delete butonu ile hafızada kalan veri silindi
    public void delete(View view){
        int storeData=sh.getInt("storedAge",0);
        if(storeData !=0){
            sh.edit().remove("storedAge").apply();
            textView.setText("Your Age : ");
        }
    }
}