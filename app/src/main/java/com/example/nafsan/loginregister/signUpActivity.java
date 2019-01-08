package com.example.nafsan.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nameEditText,emailEditText,passwordEditText,usernameEditText;
    private Button signUp;
    private UserDetails userDetails;
    private  DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText=findViewById(R.id.nameSignUpId);
        emailEditText=findViewById(R.id.emailSignUpId);
        passwordEditText=findViewById(R.id.passwordSignUpId);
        usernameEditText=findViewById(R.id.usernameSignUpId);

        databaseHelper=new DatabaseHelper(this);
        signUp=findViewById(R.id.signUpSignUpButtonId);
        userDetails=new UserDetails();
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name= nameEditText.getText().toString();
        String email= emailEditText.getText().toString();
        String password= passwordEditText.getText().toString();
        String username= usernameEditText.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setPassword(password);
        userDetails.setUsername(username);

        long rowId= databaseHelper.insertData(userDetails);

        if(rowId>0){
            Toast.makeText(getApplicationContext(),"Row"+rowId+"is successfully inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Row insertion failed",Toast.LENGTH_LONG).show();
        }
    }
}
