package fitnesstracker.com.fitnessapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_log_in.*

//  login activity starts here.!!

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        // navigation event moving from login page to registration page

        NewRegistration.setOnClickListener {

            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
