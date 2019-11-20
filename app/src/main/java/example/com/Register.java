package example.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    EditText EmailNew, PasswordNew, PasswordConfirm ;
    Button RegisterAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        EmailNew = (EditText) findViewById(R.id.txtEmailNew);
        PasswordNew = (EditText) findViewById(R.id.txtPasswordNew);
        PasswordConfirm = (EditText) findViewById(R.id.txtPasswordConfirm);

        RegisterAccount = (Button) findViewById(R.id.btnRegisterAccount);
        RegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean Registered = false;
                String Email_S = EmailNew.getText().toString();
                String NewPassword_S = PasswordNew.getText().toString();
                String ConfirmPassword_S = PasswordConfirm.getText().toString();

                if (Email_S.equals("") || NewPassword_S.equals("") || ConfirmPassword_S.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "One or more fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (NewPassword_S.equals(ConfirmPassword_S)) {
                        Boolean checkEmail = db.checkEmail(Email_S);
                        if (checkEmail == true) {
                            Boolean insert = db.insert(Email_S, NewPassword_S);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Account Registered successfully", Toast.LENGTH_SHORT).show();
                               Registered = true;

                                if (Registered == true){

                                    RegisterAccount = (Button) findViewById(R.id.btnRegisterAccount);
                                    RegisterAccount.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v){
                                            Intent i = new Intent(Register.this, MainActivity.class);
                                            startActivity(i); }


                                    });

                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Account already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }


        });

    }
}