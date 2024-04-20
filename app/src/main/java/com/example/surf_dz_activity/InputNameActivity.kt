package com.example.surf_dz_activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.surf_dz_activity.databinding.ActivityInputNameBinding

class InputNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputNameBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.nextToSurname.setOnClickListener {
            val intent = Intent(this, InputSurnameActivity::class.java)
            intent.putExtra(FIRST_NAME_KEY, binding.inputName.text.toString())
            startActivity(intent)
        }

        binding.backToMain.setOnClickListener {

            val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            val ed: SharedPreferences.Editor = sPref.edit()
            ed.clear().apply()

            finish()
        }

        binding.close.setOnClickListener {

            val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            val ed: SharedPreferences.Editor = sPref.edit()
            ed.clear().apply()

            finish()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_NAME_KEY,binding.inputName.text.toString())
    }
    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        val name = savedInstanceState?.getString(SAVE_NAME_KEY) ?: ""
        binding.inputName.setText(name)
    }
}