package com.example.surf_dz_activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.surf_dz_activity.databinding.ActivityInputAgeBinding

class InputAgeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputAgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputAgeBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadText()

        val name = intent.extras?.getString(FIRST_NAME_KEY) ?: ""
        val surname = intent.extras?.getString(LAST_NAME_KEY) ?: ""

        binding.nextToMain.setOnClickListener {

            val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            val ed: SharedPreferences.Editor = sPref.edit()
            ed.clear().apply()


            val intent = Intent(this, MainActivity::class.java)

            val screenData = bundleOf(
                FIRST_NAME_KEY to name,
                LAST_NAME_KEY to surname,
                AGE_KEY to binding.inputAge.text.toString()
            )
            intent.putExtras(screenData)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
        }

        binding.backToSurname.setOnClickListener {
            saveText()
            finish()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_AGE_KEY,binding.inputAge.text.toString())
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        val age = savedInstanceState?.getString(SAVE_AGE_KEY) ?: ""
        binding.inputAge.setText(age)
    }

    fun saveText() {
        val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putString(SAVED_AGE, binding.inputAge.text.toString())
        ed.commit()

    }

    fun loadText() {
        val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val savedText: String? = sPref.getString(SAVED_AGE, "")
        binding.inputAge.setText(savedText)
    }

}