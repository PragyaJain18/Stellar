package app.pragyajain.transaction;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity
{
    EditText input_mail, input_pswd;
    TextInputLayout inp_layout_mail, inp_layout_pswd;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        inp_layout_mail = (TextInputLayout) findViewById(R.id.input_layout_username);
        inp_layout_pswd = (TextInputLayout)findViewById(R.id.input_layout_password);
        input_mail = (EditText) findViewById(R.id.email);
        input_pswd = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
    }
    public void onclicklogin (View view)
    {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    public void new_user(View view)
    {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}
