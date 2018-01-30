package fitnesstracker.com.fitnessapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

//  registration activity starts here.!!

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // navigation event, moving back to log in page

        SignUpButtonId.setOnClickListener {
            var intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)
            SignInId.text = SignInEmailId.text
        }
    }
}
