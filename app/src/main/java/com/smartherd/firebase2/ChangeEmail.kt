package com.smartherd.firebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChangeEmail : AppCompatActivity() {

    var user_email:EditText? = null
    var user_password:EditText? = null
    var new_email:EditText? = null
    var update:Button? = null
    var firebaseauth:FirebaseAuth? = null
    var user:FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)

        user_email = findViewById(R.id.email_edit_text)
        user_password = findViewById(R.id.password_edit_text)
        new_email = findViewById(R.id.new_email_edit_text_)
        update = findViewById(R.id.btn_update)
        firebaseauth = FirebaseAuth.getInstance()
        user = firebaseauth?.currentUser

        update?.setOnClickListener {

            UpdateEmail()
        }
    }

    private fun UpdateEmail(){

        var email = user_email?.text.toString()
        var password = user_password?.text.toString()
        var newEmail = new_email?.text.toString()

        var userInfo = EmailAuthProvider.getCredential(email,password)

        user?.reauthenticate(userInfo)?.addOnCompleteListener(object : OnCompleteListener<Void>{
            override fun onComplete(p0: Task<Void>) {

                user!!.updateEmail(newEmail).addOnCompleteListener(object : OnCompleteListener<Void>{
                    override fun onComplete(task: Task<Void>) {

                        if (task.isSuccessful){

                            Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT).show()
                        }else{

                            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                    }


                })
            }


        })
    }
}
