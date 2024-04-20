package com.example.surf_dz_activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.surf_dz_activity.databinding.ActivityInputSurnameBinding


class InputSurnameActivity : AppCompatActivity() {

    //private lateinit var sPref: SharedPreferences
    private lateinit var binding: ActivityInputSurnameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputSurnameBinding.inflate(layoutInflater)
        //sPref = getSharedPreferences("INPUT_SURNAME_PREFERENCES", MODE_PRIVATE)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadText()

        val name = intent.extras?.getString(FIRST_NAME_KEY) ?: ""

        binding.nextToAge.setOnClickListener {

            val intent = Intent(this, InputAgeActivity::class.java)

            val screenData = bundleOf(
                FIRST_NAME_KEY to name,
                LAST_NAME_KEY to binding.inputSurname.text.toString(),
            )
            intent.putExtras(screenData)
            startActivity(intent)
        }

        binding.backToName.setOnClickListener {
            saveText()

            finish()
        }

        binding.close.setOnClickListener {

            val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
            val ed: SharedPreferences.Editor = sPref.edit()
            ed.clear().apply()

            val intent = Intent(this, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            startActivity(intent)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVE_SURNAME_KEY, binding.inputSurname.text.toString())
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        val surname = savedInstanceState?.getString(SAVE_SURNAME_KEY) ?: ""
        binding.inputSurname.setText(surname)
    }

    fun saveText() {
        val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

        val ed: SharedPreferences.Editor = sPref.edit()
        ed.putString(SAVED_SURNAME, binding.inputSurname.text.toString())
        ed.commit()

    }

    fun loadText() {
        val sPref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val savedText: String? = sPref.getString(SAVED_SURNAME, "")
        binding.inputSurname.setText(savedText)
    }
}