package com.example.gathr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gathr.R
import com.example.gathr.databinding.ItemReminderBinding
import com.example.gathr.entities.Reminder


// A traditional recyclerview for displaying the notes stored in roomDB
//getting context and listener from activity
class RemindersRVadapter(val context: Context, val listener:IRemRVadapter):RecyclerView.Adapter<RemindersRVadapter.ReminderViewHolder>() {

    // array to get all the reminders
    private var allReminders = ArrayList<Reminder>()

    //ViewHolder class using view binding to access ids on card
    inner class ReminderViewHolder(binding: ItemReminderBinding) : RecyclerView.ViewHolder(binding.root){
        val btnDelete = binding.btnDeleteReminder
        val title = binding.tvRemTitle
        val date = binding.tvDate
        val time = binding.tvTime
        val toggler = binding.ibToggleStatus
    }

    //members function
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        var binding: ItemReminderBinding = ItemReminderBinding.inflate(LayoutInflater.from(context),parent,false)
        val viewHolder = ReminderViewHolder(binding)
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allReminders[viewHolder.adapterPosition])
        }
        viewHolder.toggler.setOnClickListener {
            listener.onItemChecked(allReminders[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    //members function
    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {

        var currRem = allReminders[position]
        holder.title.text = currRem.reminderTitle
        holder.date.text = currRem.reminderDate
        holder.time.text = currRem.reminderTime

        if(currRem.active){
            holder.toggler.setImageResource(R.drawable.ic_baseline_notifications_active_24)
        }else{
            holder.toggler.setImageResource(R.drawable.ic_baseline_notifications_off_24)
        }
    }

    //members function
    override fun getItemCount(): Int {
       return allReminders.size
    }

    //update the list on deletion or addition

    fun updateList(List:ArrayList<Reminder>){
        allReminders.clear()
        allReminders.addAll(List)
        notifyDataSetChanged()
    }
}

//Making an interface listening to clicks on adapters icons
interface IRemRVadapter{
    fun onItemClicked(reminder: Reminder)
    fun onItemChecked(reminder: Reminder)
}