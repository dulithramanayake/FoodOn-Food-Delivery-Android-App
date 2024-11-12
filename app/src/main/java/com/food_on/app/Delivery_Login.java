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

public class Delivery_Login extends AppCompatActivity {

    TextInputLayout email, pass;
    Button Signin, Signinphone;
    TextView Forgotpassword, signup;
    FirebaseAuth Fauth;
    String emailid, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery__login);

        try {

            email = (TextInputLayout) findViewById(R.id.Demail);
            pass = (TextInputLayout) findViewById(R.id.Dpassword);
            Signin = (Button) findViewById(R.id.Loginbtn);
            Forgotpassword = (TextView) findViewById(R.id.Dforgotpass);
            Signinphone = (Button) findViewById(R.id.Dphonebtn);

            Fauth = FirebaseAuth.getInstance();

            Signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    emailid = email.getEditText().getText().toString().trim();
                    pwd = pass.getEditText().getText().toString().trim();

                    if (isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(Delivery_Login.this);
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.setCancelable(false);
                        mDialog.setMessage("Sign In Please Wait.......");
                        mDialog.show();

                        Fauth.signInWithEmailAndPassword(emailid, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
                                                    if ("DeliveryPerson".equals(userRole)) {
                                                        // Proceed to the delivery person panel
                                                        Toast.makeText(Delivery_Login.this, "Congratulation! You Have Successfully Login", Toast.LENGTH_SHORT).show();
                                                        Intent Z = new Intent(Delivery_Login.this, Delivery_FoodPanelBottomNavigation.class);
                                                        startActivity(Z);
                                                        finish();
                                                    } else {
                                                        // Display an error message if the role is not "DeliveryPerson"
                                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this, "Error", "You are not a registered Delivery person.");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                // Handle the error
                                            }
                                        });
                                    } else {
                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this, "Verification Failed", "You Have Not Verified Your Email");
                                    }
                                }else {
                                    mDialog.dismiss();
                                    // Check for specific error message
                                    String errorMessage = task.getException().getMessage();
                                    if (errorMessage.contains("INVALID_LOGIN_CREDENTIALS")) {
                                        // Show a custom error message for incorrect login credentials
                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this, "Error", "The password is incorrect or the user does not exist.");
                                    } else {
                                        // Show a generic error message for other errors
                                        ReusableCodeForAll.ShowAlert(Delivery_Login.this, "Error", errorMessage);
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
                    startActivity(new Intent(Delivery_Login.this, Delivery_ForgotPassword.class));
                    finish();
                }
            });

            Signinphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Delivery_Login.this, Delivery_registeration.class));
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

        boolean isvalid = false, isvalidemail = false, isvalidpassword = false;
        if (TextUtils.isEmpty(emailid)) {
            email.setErrorEnabled(true);
            email.setError("Email is required");
        } else {
            if (emailid.matches(emailpattern)) {
                isvalidemail = true;
            } else {
                email.setErrorEnabled(true);
                email.setError("Invalid Email Address");
            }
        }
        if (TextUtils.isEmpty(pwd)) {

            pass.setErrorEnabled(true);
            pass.setError("Password is Required");
        } else {
            isvalidpassword = true;
        }
        isvalid = (isvalidemail && isvalidpassword) ? true : false;
        return isvalid;
    }

    public void onBackPressed() {
        // Navigate back to MainActivity
        super.onBackPressed();
        Intent intent = new Intent(Delivery_Login.this, MainMenu.class);
        startActivity(intent);
        finish(); // Close the current activity
    }
}
