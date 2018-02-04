package fitnesstracker.com.fitnessapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_log_in.*

//  login activity starts here.!!

class LogInActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var currentUser : FirebaseUser? = null
    var email : String? = null
    var password : String? = null
    var VEmail : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        if (intent.extras != null){
            VEmail = intent.extras.get("email").toString()
        }
        mAuth = FirebaseAuth.getInstance()

        // sign in existing user

        SignInButtonId.setOnClickListener {

            this.email = SignInId.text.toString().trim()
            this.password = SignInPasswordId.text.toString().trim()
            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
                loginUser(email!!, password!!)
            }

        }
        // navigation event moving from login page to registration page

        NewRegistration.setOnClickListener {

            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun loginUser(email : String, password : String){
        mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener {

                task: Task<AuthResult> ->
                if (task.isSuccessful){
                    var user : FirebaseUser = mAuth!!.currentUser!!

                    Log.d("User",user.email.toString())
                    Toast.makeText(this,"Successfully Login.", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"Login failed.", Toast.LENGTH_LONG).show()
                    Log.d("User",task.exception.toString())
                }
            }
    }

}
