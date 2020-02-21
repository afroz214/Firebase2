package com.smartherd.firebase2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.*

class StorageActivity : AppCompatActivity() {

    var upload_btn:Button? = null
    var image_view:ImageView? = null
    var imageUri:Uri? = null
    var storage:FirebaseStorage? = null
    var imageRef:StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        upload_btn = findViewById(R.id.btn_image_upload)
        image_view = findViewById(R.id.imageView)
        storage = FirebaseStorage.getInstance()
        imageRef = storage?.reference?.child("Images")

        image_view?.setOnClickListener {

            PickImageFromGallery()
        }

        upload_btn?.setOnClickListener {

            AddTheImageToFirebase()
        }
    }


    private fun PickImageFromGallery(){

        //This is use for opening the gallery

        val gallery = Intent()
        gallery.type = "Images/*"
        gallery.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(gallery, 111)


    }
    //This is use to getting an images from ur gallery

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {


        if (requestCode == 111 && resultCode == Activity.RESULT_OK && data != null){

            imageUri = data.data

            //Instead of try and catch method you can use this
            image_view?.setImageURI(imageUri)

            //if you want then u can remove the try and catch method, its just for avoiding any issue

           // try {
               // val image = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
               // image_view?.setImageBitmap(image)

           // }catch (error:IOException){
               // Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()


           // }
        }
    }

    private fun AddTheImageToFirebase(){

        //this is use to upload the image

        if (imageUri != null ){

            val ref = imageRef?.child(UUID.randomUUID().toString())
            ref?.putFile(imageUri!!)?.addOnCompleteListener(object : OnCompleteListener<UploadTask.TaskSnapshot>{
                override fun onComplete(task: Task<UploadTask.TaskSnapshot>) {

                    if (task.isSuccessful){
                        Toast.makeText(applicationContext, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    }else{

                        Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                }


            })
        }


    }
}
