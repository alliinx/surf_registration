package com.example.surf_dz_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.surf_dz_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.extras?.getString(FIRST_NAME_KEY) ?: ""
        val surname = intent.extras?.getString(LAST_NAME_KEY) ?: ""
        val age = intent.extras?.getString(AGE_KEY) ?: ""

        if(name!="" && surname!="" && age!=""){
            Toast.makeText(this,"Добро пожаловать $name $surname",Toast.LENGTH_LONG).show()
        }

        binding.startButton.setOnClickListener {
            val intent = Intent(this, InputNameActivity::class.java)
            startActivity(intent)
        }

    }
}