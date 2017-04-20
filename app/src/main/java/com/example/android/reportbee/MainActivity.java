package com.example.android.reportbee;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText inputEmail;
    private EditText inputPassword;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialization
        btnLogin = (Button) findViewById(R.id.btnLogin);
        builder = new AlertDialog.Builder(MainActivity.this);
        inputPassword = (EditText) findViewById(R.id.password);
        inputEmail = (EditText) findViewById(R.id.email);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                //Validating email and password
                if (password.equals("") && email.equals("")) {
                    builder.setTitle("Something Went Wrong....");
                    displayAlert("Enter Valid Username And Password....");
                } else if (email.equals("teacher@gmail.com") && password.equals("teacherpw")) {
                    Intent intent = new Intent(MainActivity.this, CalenderUI.class);
                    startActivity(intent);
                } else {
                    builder.setTitle("Something Went Wrong....");
                    displayAlert("InValid Username And Password....");
                }
            }
        });
    }

    //Function for showing alert message
    public void displayAlert(final String message) {
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inputPassword.setText("");
                inputEmail.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}