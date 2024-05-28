
package  com.calmscient.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.calmscient.R
import com.calmscient.databinding.ActivitySetNewPasswordBinding

class SetNewPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivitySetNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetNewPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.imgBack.setOnClickListener {
            navigateToLoginActivity()
        }

        binding.btnUpdate.setOnClickListener {
            val password = binding.editPassword.text.toString()
            val confirmPassword = binding.confirmEditPassword.text.toString()

            if(password.isEmpty() || confirmPassword.isEmpty())
            {
                showPositiveDialog(false)
            } else if (password == confirmPassword) {
                showPositiveDialog(true)
            } else {
                showPositiveDialog(false)
            }
        }
    }

    override fun onBackPressed() {
        navigateToLoginActivity()
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, VerifyForgotPasswordActivity::class.java))
        finish()
    }

    @SuppressLint("MissingInflatedId")
    private fun showPositiveDialog(matched: Boolean) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.layout_password_dialog, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.yes_imageview)
        val textView = dialogView.findViewById<TextView>(R.id.tv_right)
        val continueButton = dialogView.findViewById<AppCompatButton>(R.id.continueButton)

        if (matched) {
            imageView.setImageResource(R.drawable.password_update_success_dialog) // Set tick icon
            textView.text = "Updated successfully " // Set success message
            continueButton.text = "Continue" // Set continue button text
        } else {
            imageView.setImageResource(R.drawable.anixiety_6_3_4_cross) // Set wrong icon
            textView.text = "Password Not Matched !!!" // Set wrong message
            continueButton.text = "Retry" // Set retry button text
        }

        val dialogBuilder = AlertDialog.Builder(this, R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        // Handle the close button click
        continueButton.setOnClickListener {
            dialog.dismiss()
            if (matched) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}
