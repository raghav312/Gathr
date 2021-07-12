package com.example.gathr.fragments
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gathr.R
import com.example.gathr.ViewModel.ReminderViewModel
import com.example.gathr.databinding.FragmentCreateReminderBinding
import com.example.gathr.entities.Reminder
import com.example.gathr.utils.AlarmReceiver
import com.example.gathr.utils.NotificationHelper
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

//Create a remainder fragment
class CreateReminderFragment : Fragment() {

    lateinit var binding: FragmentCreateReminderBinding
    lateinit var viewModel: ReminderViewModel

    //a calendar instance to store the date and time for reminder
    private var calendar: Calendar= Calendar.getInstance()

    //declaring late initializing variables
    private lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //providing layout to view
        binding = FragmentCreateReminderBinding.inflate(layoutInflater)

        binding.btnDone.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                checkParameter()
            }
        })

        //pop this frag from stack of fragments
        binding.btnBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        })

        //calling transformDatePicker to show the dialog box
        binding.etDate.transformIntoDatePicker(requireContext(),"MM/dd/yyyy",Date())


        //calling the function to show time picker dialog box
        binding.etTime.setOnClickListener(object :View.OnClickListener {
            override fun onClick(v: View?) {
                transformIntoTimePicker(requireContext())
            }
        })

        return binding.root

    }


    //function to check parameters
    private fun checkParameter() {

        //Checking different fields for data
        if(binding.etTitle.text.toString().isEmpty()) {
            Snackbar.make(binding.root, "Please Enter Title", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()

        } else if (binding.etDate.text.toString().isEmpty()) {

            Snackbar.make(binding.root, "Please Enter Date", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        } else if (binding.etTime.text.toString().isEmpty()) {

            Snackbar.make(binding.root, "Please Enter Time", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()

        } else {

            //make reminder instance
            val reminder = Reminder(
                binding.etTitle.text.toString(),
                binding.etDate.text.toString(),
                binding.etTime.text.toString(),
                true
            )

            //making reminder viewModel instance and adding it to the recycler view

            viewModel = ViewModelProvider(this).get(ReminderViewModel::class.java)
            viewModel.insertReminder(reminder)

            //set the current reminder instance as alarm
            setAlarm(reminder)

            //pop this stack up
            requireActivity().supportFragmentManager.popBackStack()

        }
    }


    //function of set a timely notfifcation to the system
    private fun setAlarm(reminder: Reminder) {

        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //Calling the class which sets up the receiver
        val intent = Intent(requireContext(),AlarmReceiver::class.java)
        //providing extra details to the intent/class
        intent.putExtra("title",reminder.reminderTitle)

        //make a pending which require context and intent which broadcasts to the system
        pendingIntent = PendingIntent.getBroadcast(requireContext(),0,intent,0)

        //set repeating interval if any
        //Not in our case
        //providing realtime clock and pending intent
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP , calendar.timeInMillis,0,pendingIntent)
        Toast.makeText(requireContext(), "Reminder Set", Toast.LENGTH_SHORT).show()
    }


    //date picker
    private fun EditText.transformIntoDatePicker(context: Context, format: String, minDate: Date? = null) {
        //xml attributes
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false


        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat(format, Locale.UK)
                setText(sdf.format(myCalendar.time))
            }

        //set the date on click
        setOnClickListener {
            DatePickerDialog(
                context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                minDate?.time?.also { datePicker.minDate = it }
                show()
            }
        }

        calendar[Calendar.YEAR] = myCalendar.get(Calendar.YEAR)
        calendar[Calendar.MONTH] = myCalendar.get(Calendar.MONTH)
        calendar[Calendar.DAY_OF_MONTH] = myCalendar.get(Calendar.DAY_OF_MONTH)
    }

    //time picker
    private fun transformIntoTimePicker(context:Context){
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog = TimePickerDialog(context,{ timePicker, selectedHour, selectedMinute ->
            binding.etTime.setText("$selectedHour:$selectedMinute")
            calendar[Calendar.HOUR_OF_DAY] = selectedHour
            calendar[Calendar.MINUTE] = selectedMinute  },hour,minute,true) //Yes 24 hour time
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
    }


}