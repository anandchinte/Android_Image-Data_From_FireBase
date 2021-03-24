package com.example.chintedownloadimage

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        getUserFromFireStore()


    }


    fun getUserFromFireStore(){

        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener {

                val result: StringBuffer = StringBuffer()
                val imageURL : StringBuffer = StringBuffer()

                if(it.isSuccessful){
                    for(document in it.result!!){
                        result.append(document.data.getValue("firstName")).append(" ")
                            .append(document.data.getValue("lastName"))

                        imageURL.append(document.data.getValue("image"))
                    }

                    var textView1 = findViewById<TextView>(R.id.text_view1)
                    textView1.text = result

                    var imageView = findViewById<ImageView>(R.id.image_view1)

                    //var url = "https://drive.google.com/file/d/1Uo-aDHWock8JSp-NUiajsZRBe-ECwT8X/view?usp=sharing"
                    //var url = "https://drive.google.com/uc?export=view&id=1CV532cb-anmbEQlyswZ1bgZ36C1S3W_d"

//                    val storage = FirebaseStorage.getInstance().getReferenceFromUrl(imageURL.toString())
//                    //val refURL = storage.getReferenceFromUrl("gs://chintefirebase1.appspot.com/IMG_0064.jpg")
                    Log.i("url", imageURL.toString())
                    GlideApp.with(this)
                        .load(imageURL.toString())
                        .fitCenter()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(imageView)

                }
            }

    }
}