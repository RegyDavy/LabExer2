package com.caampued.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Input extends AppCompatActivity {

    EditText username, password;
    Button btn_Shared, btn_Intern, btn_Next;
    SharedPreferences.Editor editor;
    FileOutputStream fos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_Shared = (Button) findViewById(R.id.btn_Shared);
        btn_Intern = (Button) findViewById(R.id.btn_Intern);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(" ", MODE_PRIVATE);
        editor = pref.edit();
        gotoOutput();
    }


    public void saveShared (View view){

        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();


    }
    public void saveIntern (View view){
        String UsName = username.getText().toString();
        String PassWd = password.getText().toString();
        try {
            fos = openFileOutput("user.txt", Context.MODE_PRIVATE);
            fos.write((UsName.getBytes()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fos = openFileOutput("pass.txt", Context.MODE_PRIVATE);
            fos.write((PassWd.getBytes()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void gotoOutput (){
        btn_Next = (Button) findViewById(R.id.btn_Next);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoresultviaAns1 = new Intent(Input.this, Output.class);
                startActivity(gotoresultviaAns1);
            }
        });
    }
}
