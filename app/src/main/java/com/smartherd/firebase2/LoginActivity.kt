package com.smartherd.firebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var user_email:EditText? = null
    private var user_password:EditText? = null
    private var login_btn:Button? = null
    private var firebaseAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_email = findViewById(R.id.user_email_login)
        user_password = findViewById(R.id.user_password_login)
        login_btn = findViewById(R.id.login_btn_activity)
        firebaseAuth = FirebaseAuth.getInstance()

        reset_btn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, PasswordReset::class.java))
        }

        login_btn?.setOnClickListener {

            LoginUser()

        }
    }

    private fun LoginUser(){

        var email = user_email?.text.toString()
        var password = user_password?.text.toString()

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){

            Toast.makeText(applicationContext, "this field annot be empty", Toast.LENGTH_SHORT).show()
        }else{

            firebaseAuth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {

                    if (task.isSuccessful){

                        Toast.makeText(applicationContext, "You are logged in", Toast.LENGTH_SHORT).show()

                        val user:FirebaseUser = firebaseAuth?.currentUser!!

                        if (user.isEmailVerified){
                            startActivity(Intent(this@LoginActivity, TestActivity::class.java))
                        }else{

                            Toast.makeText(applicationContext, "Account not verified", Toast.LENGTH_SHORT).show()
                        }
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }


            })
        }
    }
}
