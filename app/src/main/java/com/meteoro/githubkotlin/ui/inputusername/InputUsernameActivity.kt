package com.meteoro.githubkotlin.ui.inputusername

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.meteoro.githubkotlin.R
import com.meteoro.githubkotlin.ui.user.presentation.UserActivity

/**
 * Created by meteoro on 21/02/2018.
 **/
class InputUsernameActivity : AppCompatActivity() {

    lateinit var inputUsername: EditText
    lateinit var buttonConfirmation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_username)

        inputUsername = findViewById(R.id.input_username)
        buttonConfirmation = findViewById(R.id.button_confirmation)

        buttonConfirmation.setOnClickListener {
            val username = inputUsername.text.toString()
            if (username.isEmpty()) {
                Snackbar.make(inputUsername, "Preencha o campo", Snackbar.LENGTH_SHORT).show()
            } else {
                val intent = UserActivity.newIntent(this, username)
                startActivity(intent)
            }
        }
    }
}