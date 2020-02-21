package com.smartherd.firebase2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_information.*
import org.w3c.dom.Text

class UserInformation : AppCompatActivity() {

    var firstName:TextView? = null
    var lastName:TextView? = null
    var userName:TextView? = null
    var firebaseAuth:FirebaseAuth? = null
    var firebaseDatabase:DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_information)

        firstName = findViewById(R.id.firstName_textview)
        lastName = findViewById(R.id.lastName_textview)
        userName = findViewById(R.id.userName_textview)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseAuth?.currentUser!!.uid)

        firebaseDatabase?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(task: DataSnapshot) {

                val firstname = task.child("firstName").value as String
                val lastname = task.child("lastName").value as String
                val username = task.child("userName").value as String


                firstName?.text = firstname
                lastName?.text = lastname
                userName?.text = username


                update_information.setOnClickListener {
                    startActivity(Intent(this@UserInformation, ProfileActivity::class.java))
                }
                btn_change_email.setOnClickListener {
                    startActivity(Intent(this@UserInformation, ChangeEmail::class.java))
                }

            }


        })

    }
}
