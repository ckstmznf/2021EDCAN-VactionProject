package com.example.a2021edcanvacationproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvacationproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var db : FirebaseFirestore

    lateinit var id : String
    lateinit var password : String
    lateinit var email : String
    

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        binding.txtLoginSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoginLogin.setOnClickListener {
            with(binding){
                id = edtLoginInputId.text.toString()
                password = edtLoginInputPassword.text.toString()
                
                if(TextUtils.isEmpty(id)){
                    edtLoginInputId.error = "ID를 입력하세요."
                    return@setOnClickListener
                }   else if(TextUtils.isEmpty(password)){
                    edtLoginInputPassword.error = "비밀번호를 입력하세요."
                    return@setOnClickListener
                }

                db.collection("userData").document(id)
                    .get()
                    .addOnSuccessListener {
                        email = it.data?.get("email").toString()

                        auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener {
                                Toast.makeText(this@LoginActivity, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@LoginActivity, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()
                    }
            }
        }

    }
}
