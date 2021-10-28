package com.example.a2021edcanvactionproject.Activity.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a2021edcanvactionproject.R
import com.example.a2021edcanvactionproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var navController : NavController

    val a = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val bottomNav = binding.navBar
        navController = findNavController(R.id.fragmentContainerView)

        bottomNav.setupWithNavController(navController)
    }

//    fun gotoMain(){
//        navController.navigate(R.id.action_addTodoFragment_to_mainFragment)
//    }
}