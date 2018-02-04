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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

//  registration activity starts here.!!

class SignUpActivity : AppCompatActivity() {


    var mAuth: FirebaseAuth? = null
    var mDatabase : DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        SignUpButtonId.setOnClickListener {
            var email = SignInEmailId.text.toString().trim()
            var pass = SignInPassId.text.toString().trim()
            var confPass = ConfirmPasswordId.text.toString().trim()
            var userName = SignUpNameId.text.toString().trim()

            if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass) || !TextUtils.isEmpty(userName)){
                if(confPass.equals(pass)){
                    createAccount(email, pass, userName)
                }
            }
            else{
                Toast.makeText(this,"Password Doesnt Match.!!",Toast.LENGTH_SHORT).show()
            }

        }

        }
    fun createAccount(email:String, password : String, userName : String){
        mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            task: Task<AuthResult> ->
                        if (task.isSuccessful){
                            var currentUser : FirebaseUser = mAuth!!.currentUser!!
                            var userId = currentUser!!.uid

                            mDatabase = FirebaseDatabase.getInstance().reference
                                    .child("Users").child(userId)


                            var userObject = HashMap<String, String>()
                            userObject.put("email", email)
                            userObject.put("user name", userName)
                            userObject.put("password", password)

                            mDatabase!!.setValue(userObject).addOnCompleteListener {
                                task: Task<Void> ->
                                    if (task.isSuccessful){
                                        Toast.makeText(this,"successful registered.",Toast.LENGTH_SHORT).show()
                                        var intentLogin = Intent(this,LogInActivity::class.java)
                                        intentLogin.putExtra("email",email)
                                        startActivity(intentLogin)
                                        finish()
                                    }
                                else{
                                        Toast.makeText(this,"unsuccessful operation.",Toast.LENGTH_SHORT).show()
                                    }
                            }
//                            var intent = Intent(this,LogInActivity::class.java)
//                            startActivity(intent)
//                            SignInId.text = SignInEmailId.text
                        }
                        else {
                            Log.d("error",task.exception.toString())
                        }
                }
    }

}

