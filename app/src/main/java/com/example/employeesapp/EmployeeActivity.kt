package com.example.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_employee.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val employeeString = bundle!!.getString("employee")
            val employee = JSONObject(employeeString)

            val name = employee["firstName"].toString() + " " + employee["lastName"].toString()
            val title = employee["title"]
            val email = employee["email"]
            val phone = employee["phone"]
            val department = employee["department"]
            val image = employee["image"]

            nameTextView.text = name.toString()
            titleTextView.text = title.toString()
            emailTextView.text = email.toString()
            phoneTextView.text = phone.toString()
            departmentTextView.text = department.toString()

            val options = RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)

            Glide
                .with(this)
                .load(image.toString())
                .apply(options)
                .into(profileImageView)
        }
    }
}