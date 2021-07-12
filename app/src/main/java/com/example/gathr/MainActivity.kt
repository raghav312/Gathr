package com.example.gathr

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.gathr.ui.main.SectionsPagerAdapter
import com.example.gathr.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
    //pre coded from android studio when making an tabbed layout activity

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //making a section pager adapter
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager

        //inserting an adapter in the viewPager
        viewPager.adapter = sectionsPagerAdapter

        //setting up TabLayout
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }

}