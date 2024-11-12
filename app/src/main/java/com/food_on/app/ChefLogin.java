package com.food_on.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.food_on.app.R;
import com.food_on.app.ReusableCode.ReusableCodeForAll;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChefLogin extends AppCompatActivity {


    TextInputLayout email, pass;
    Button Signin, Signinphone;
    TextView Forgotpassword;
    FirebaseAuth Fauth;
    String em;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_login);

        try {

            email = (TextInputLayout) findViewById(R.id.Lemail);
            pass = (TextInputLayout) findViewById(R.id.Lpassword);
            Signin = (Button) findViewById(R.id.button4);
            Forgotpassword = (TextView) findViewById(R.id.forgotpass);
            Signinphone = (Button) findViewById(R.id.btnphone);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    em = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if (isValid()) {
                        final ProgressDialog mDialog = new ProgressDialog(ChefLogin.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(em, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mDialog.dismiss();

                                    // Check if the user's email is verified
                                    if (Fauth.getCurrentUser().isEmailVerified()) {
                                        mDialog.dismiss();

                                        // Retrieve the user's role from the database
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    String userRole = dataSnapshot.child("Role").getValue(String.class);
                                                    if ("Chef".equals(userRole)) {
                                                        // Proceed to the chef panel
                                                        Toast.makeText(ChefLogin.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                                        Intent Z = new Intent(ChefLogin.this, ChefFoodPanel_BottomNavigation.class);
                                                        startActivity(Z);
                                                        finish();
                                                    } else {
                                                        // Display an error message if the role is not "Chef"
                                                        ReusableCodeForAll.ShowAlert(ChefLogin.this, "Error", "You are not a registered Chef.");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle the error
                                            }
                                        });
                                    } else {
                                        ReusableCodeForAll.ShowAlert(ChefLogin.this, "Verification Failed", "You Have Not Verified Your Email");
                                    }
                                } else {
                                    mDialog.dismiss();
                                    // Check for specific error message
                                    String errorMessage = task.getException().getMessage();
                                    if (errorMessage.contains("INVALID_LOGIN_CREDENTIALS")) {
                                        // Show a custom error message for incorrect login credentials
                                        ReusableCodeForAll.ShowAlert(ChefLogin.this, "Error", "The password is incorrect or the user does not exist.");
                                    } else {
                                        // Show a generic error message for other errors
                                        ReusableCodeForAll.ShowAlert(ChefLogin.this, "Error", errorMessage);
                                    }
                                }
                            }
                        });
                    }
                }
            });

            Forgotpassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ChefLogin.this, ChefForgotPassword.class));
                    finish();
                }
            });

            Signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ChefLogin.this, ChefRegisteration.class));
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        email.setErrorEnabled(false);
        email.setError("");
        pass.setErrorEnabled(false);
        pass.setError("");

        boolean isvalidemail = false, isvalidpassword = false, isvalid = false;
        if (TextUtils.isEmpty(em)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        } else {
            if (em.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setErrorEnabled(true);
                email.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(pwd)) {
            pass.setErrorEnabled(true);
            pass.setError("Password is required");
        } else {
            isvalidpassword = true;
        }
        isvalid = (isvalidemail && isvalidpassword) ? true : false;
        return isvalid;
    }
    public void onBackPressed() {
        // Navigate back to MainActivity
        super.onBackPressed();
        Intent intent = new Intent(ChefLogin.this, MainMenu.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}


