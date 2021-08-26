package com.example.a2021edcanvacationproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.a2021edcanvacationproject.databinding.ActivitySignUpBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class SignUpActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var db : FirebaseFirestore

    private lateinit var binding : ActivitySignUpBinding


    private lateinit var id : String
    private lateinit var email : String
    private lateinit var name : String
    private lateinit var password : String
    private lateinit var passwordRe : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        binding.btnSignUpSignUp.setOnClickListener {
            with(binding){
                id = edtSignUpInputId.text.toString()
                email = edtSignUpInputEmail.text.toString()
                name = edtSignUpInputName.text.toString()
                password = edtSignUpInputPassword.text.toString()
                passwordRe = edtSignUpInputPasswordRe.text.toString()

                if(TextUtils.isEmpty(id)){
                    edtSignUpInputId.error = "ID를 입력하세요."
                    return@setOnClickListener
                }   else if(TextUtils.isEmpty(email)){
                    edtSignUpInputEmail.error = "이메일을 입력하세요."
                    return@setOnClickListener
                }   else if(TextUtils.isEmpty(name)){
                    edtSignUpInputName.error = "이름을 입력하세요."
                    return@setOnClickListener
                }   else if(TextUtils.isEmpty(password)){
                    edtSignUpInputId.error = "비밀번호를 입력하세요."
                    return@setOnClickListener
                }   else if(TextUtils.isEmpty(passwordRe)){
                    edtSignUpInputId.error = "비밀번호를 다시 입력하세요."
                    return@setOnClickListener
                }   else if(password != passwordRe){
                    edtSignUpInputPassword.error = "비밀번호가 서로 다릅니다."
                    edtSignUpInputPasswordRe.error = "비밀번호가 서로 다릅니다."
                    return@setOnClickListener
                }
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    var userData = hashMapOf(
                        "name" to name,
                        "email" to email,
                    )

                    db.collection("userData").document(id)
                        .set(userData)
                        .addOnSuccessListener {
//                            val intent = Intent(this, LoginActivity::class.java)
                            Toast.makeText(this, "회원가입에 성공하였습니다.", Toast.LENGTH_LONG).show()

                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show()
                        }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_LONG).show()
                }



        }
    }
}