package com.example.gathr.fragments

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gathr.R
import com.example.gathr.ViewModel.ReminderViewModel
import com.example.gathr.adapters.IRemRVadapter
import com.example.gathr.adapters.RemindersRVadapter
import com.example.gathr.databinding.FragmentReminderBinding
import com.example.gathr.entities.Reminder
import com.example.gathr.utils.AlarmReceiver
import com.example.gathr.utils.NotificationHelper

//Displays all the reminders and shows icons to delete or add a reminder
class ReminderFragment : Fragment(),View.OnClickListener,IRemRVadapter {

    private lateinit var binding: FragmentReminderBinding
    lateinit var viewModel: ReminderViewModel
    private lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReminderBinding.inflate(layoutInflater)
        val view: View = binding.root

        //displaying the adapter in linear layout
        binding.rvReminders.layoutManager = LinearLayoutManager(requireContext())
        var adapter = RemindersRVadapter(requireContext(),this)


        binding.rvReminders.adapter = adapter

        //get respective viewModel instance and observer the changes in db through reposotory
        //(MVVM)
        //(also used in notes fragments)
        viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        viewModel.allReminders.observe(viewLifecycleOwner, Observer {
            if(it!= null){
                adapter.updateList(it as ArrayList<Reminder>)
            }
        })

        binding.fabAddReminder.setOnClickListener(this)
        return view

    }

    //Makes a notification channel to store and trigger alarms/notifs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //calling the static method of NotificationHelper class
        NotificationHelper.createNotificationChannel(requireContext(),
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabAddReminder -> replaceFragment(CreateReminderFragment() , false)
        }
    }


    // same function as before(code re-usability)
    private fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.reminders_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

    //delete the item on click
    //notify the viewModel
    override fun onItemClicked(reminder: Reminder) {
        cancelReminder(reminder)
        viewModel.deleteReminder(reminder)
    }

    //Cancel the reminder i.e. delete from the notification channel
    private fun cancelReminder(reminder: Reminder){
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(),AlarmReceiver::class.java)
        intent.putExtra("title",reminder.reminderTitle)
        Log.i("dataisnew","${reminder.reminderTitle} is delete")
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0,intent,0)
        alarmManager.cancel(pendingIntent)
    }

    //make reminder status not active(cancel reminder)
    override fun onItemChecked(reminder: Reminder) {
        reminder.active = !reminder.active
    }

}