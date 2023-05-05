package org.bedu.movies_app_android.Login
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.bedu.movies_app_android.R
import java.io.Serializable

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val registerButton = findViewById<Button>(R.id.register_button)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both fields.", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent().apply {
                    putExtra("newUser", LoginActivity.User(username, password) as Serializable)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}