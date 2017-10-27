package com.caampued.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.id;
import static android.R.attr.label;

public class Output extends AppCompatActivity {

    TextView displayUsername, displayPassword, displayclear;
    Button btn_LoadShared, btn_LoadIntern, btn_Clear, btn_goBack;
    SharedPreferences pref;
    FileInputStream fis;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        displayUsername = (TextView) findViewById(R.id.displayUsername);
        displayPassword = (TextView) findViewById(R.id.displayPassword);
        btn_LoadShared = (Button) findViewById(R.id.btn_LoadShared);
        btn_LoadIntern = (Button) findViewById(R.id.btn_LoadIntern);
        btn_Clear = (Button) findViewById(R.id.btn_Clear);
        pref = getApplication().getSharedPreferences(" ", MODE_PRIVATE);

        gotoInput();
    }

    public void shared (View view){
        String user = pref.getString("username", "");
        String pwd = pref.getString("password", "");
        displayUsername.setText("User is" + " " + user);
        displayPassword.setText("Password is" + " " + pwd);

    }

    public void internal (View view){
        StringBuffer user = new StringBuffer();
        StringBuffer pass = new StringBuffer();
        int read = 0;
        try {
            fis = openFileInput("user.txt");
            while ((read = fis.read()) != -1) {
                user.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis = openFileInput("pass.txt");
            while ((read = fis.read()) != -1) {
                pass.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayUsername.setText("User is" +" "+user.toString());
        displayPassword.setText("Password is" + " "+pass.toString());
    }



    public void clearText (View view){
        Toast.makeText(this, "Data Cleared", Toast.LENGTH_SHORT).show();
        displayUsername.setText(" ");
        displayPassword.setText(" ");
    }
    public void gotoInput (){
        btn_goBack = (Button) findViewById(R.id.btn_goBack);
        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoinput = new Intent(Output.this, Input.class);
                startActivity(gotoinput);
            }
        });
    }


}
