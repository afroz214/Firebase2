package com.smartherd.firebase2

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

class PasswordReset : AppCompatActivity() {

    private var user_email:EditText? = null
    private var button_reset:Button? = null
    private var firebase:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)


        user_email = findViewById(R.id.user_email_editText)
        button_reset = findViewById(R.id.btn_reset)
        firebase = FirebaseAuth.getInstance()

        button_reset?.setOnClickListener {

          ResetPassword()
        }
    }
    private fun ResetPassword(){

        var email = user_email?.text.toString()

        if (TextUtils.isEmpty(email)){
            Toast.makeText(applicationContext, "please enter ur email", Toast.LENGTH_SHORT).show()
        }else{

            firebase?.sendPasswordResetEmail(email)?.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "check the link of ur email", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }


            })
        }
    }
}
