package com.example.nafsan.loginregister;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    DatabaseHelper databaseHelper;
    private Button signInButton,signUpButton;
    private EditText usernameEditText,passwordEditText;
    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.signInButtonId);
        signUpButton = findViewById(R.id.signUpButtonId);
        usernameEditText = findViewById(R.id.emailId);
        passwordEditText = findViewById(R.id.passwordId);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();

        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if(v.getId()==R.id.signInButtonId) {
            Boolean result= databaseHelper.findPassword(username,password);
            if(result==true)
            {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        }
        else{
            Intent intent=new Intent(MainActivity.this,signUpActivity.class);
            startActivity(intent);
        }
    }
}
