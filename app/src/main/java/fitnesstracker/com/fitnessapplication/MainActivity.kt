package fitnesstracker.com.fitnessapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*

// main activity starts here..!!


class MainActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mDatabase : DatabaseReference? = null
    var mAuthListener : FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {

            firebaseAuth: FirebaseAuth ->
                var user = firebaseAuth.currentUser
                if (user != null){
                    // go the Map Dashboard
//                    startActivity(Intent(this, MainMapActivity :: class.java))
                    finish()
                }
            else{
                    Toast.makeText(this,"Login Required.!!",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LogInActivity :: class.java))
                    finish()
                }
        }

        MainActStartButton.setOnClickListener {
            var intent = Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null){
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }
}
