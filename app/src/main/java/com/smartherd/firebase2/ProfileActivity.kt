package com.smartherd.firebase2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {

    var user_firstName:EditText? = null
    var user_lastName:EditText? = null
    var user_userName:EditText? = null
    var update_btn:Button? = null
    var firebaseauth:FirebaseAuth? = null
    var firebasedatabase:DatabaseReference? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        user_firstName = findViewById(R.id.user_first_name)
        user_lastName = findViewById(R.id.user_last_name)
        user_userName = findViewById(R.id.user_username)
        update_btn = findViewById(R.id.btn_update)
        firebaseauth = FirebaseAuth.getInstance()
        firebasedatabase = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseauth?.currentUser!!.uid)

        update_btn?.setOnClickListener {

            SaveUserInfo()
        }
    }

    private fun SaveUserInfo(){

        val firstname = user_firstName?.text.toString()
        val lastname = user_lastName?.text.toString()
        val username = user_userName?.text.toString()

        if (TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && TextUtils.isEmpty(username)){

            Toast.makeText(applicationContext, "This field can't be empty", Toast.LENGTH_SHORT).show()
        }else{

            val userInfo = HashMap<String, Any>()
            userInfo.put("firstName",firstname)
            userInfo.put("lastName", lastname)
            userInfo.put("userName", username)

            firebasedatabase?.updateChildren(userInfo)!!.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Information updated", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }


            })
        }
    }
}
