package com.example.gathr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gathr.R
import com.example.gathr.ViewModel.ReminderViewModel
import com.example.gathr.adapters.IRemRVadapter
import com.example.gathr.adapters.RemindersRVadapter
import com.example.gathr.databinding.FragmentReminderBinding
import com.example.gathr.entities.Reminder

class ReminderFragment : Fragment(),View.OnClickListener,IRemRVadapter {
    private lateinit var binding: FragmentReminderBinding
    lateinit var viewModel: ReminderViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReminderBinding.inflate(layoutInflater)
        val view: View = binding.root

        binding.rvReminders.layoutManager = LinearLayoutManager(requireContext())
        var adapter = RemindersRVadapter(requireContext(),this)
        binding.rvReminders.adapter = adapter

        viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
        viewModel.allReminders.observe(viewLifecycleOwner, Observer {
            if(it!= null){
                adapter.updateList(it as ArrayList<Reminder>)
            }
        })

        binding.fabAddReminder.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabAddReminder -> replaceFragment(CreateReminderFragment() , false)
        }
    }

    private fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.reminders_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }

    override fun onItemClicked(reminder: Reminder) {
        viewModel.deleteReminder(reminder)
    }


    override fun onItemChecked(reminder: Reminder) {
        reminder.active = !reminder.active
        viewModel.changeActive(reminder)
    }

}