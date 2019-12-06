package example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtEmail, txtPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnRegister = (Button) findViewById(R.id.btnRegisterPage);
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i); }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = txtEmail.getText().toString();
                String Password = txtPassword.getText().toString();
                if (Email.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkEmail = db.checkEmail(Email);
                    if (checkEmail == false) {
                        Toast.makeText(getApplicationContext(), "Email is correct",
                                Toast.LENGTH_SHORT).show();
                        Boolean checkPassword = db.checkPassword(Email, Password);
                        if (checkPassword == true) {
                            Toast.makeText(getApplicationContext(), "Login Successful",
                                    Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this, MapsActivity.class);
                            startActivity(i);

                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}