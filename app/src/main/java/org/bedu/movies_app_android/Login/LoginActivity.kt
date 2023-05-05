package org.bedu.movies_app_android.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.bedu.movies_app_android.R
import java.io.Serializable

class LoginActivity : AppCompatActivity() {

    private val users = mutableListOf(User("admin", "admin"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields.", Toast.LENGTH_SHORT).show()
            } else {
                val user = users.find { it.username == username && it.password == password }
                if (user != null) {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                } else {
                    Toast.makeText(this, "Invalid username or password.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newUser = data?.getSerializableExtra("newUser") as User
            users.add(newUser)
        }
    }

    data class User(val username: String, val password: String) : Serializable
}