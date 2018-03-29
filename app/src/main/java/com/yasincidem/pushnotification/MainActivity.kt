package com.yasincidem.pushnotification

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showInstanceId.setOnClickListener {
            Toast.makeText(this, FirebaseInstanceId.getInstance().id, Toast.LENGTH_SHORT).show()
        }
        showToken.setOnClickListener({
            Toast.makeText(this, FirebaseInstanceId.getInstance().token, Toast.LENGTH_SHORT).show()
        })
        showCreationTime.setOnClickListener({
            Toast.makeText(this, FirebaseInstanceId.getInstance().creationTime.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}
