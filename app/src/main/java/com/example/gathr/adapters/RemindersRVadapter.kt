package com.example.gathr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gathr.R
import com.example.gathr.databinding.ItemReminderBinding
import com.example.gathr.entities.Reminder

class RemindersRVadapter(val context: Context, val listener:IRemRVadapter):RecyclerView.Adapter<RemindersRVadapter.ReminderViewHolder>() {
    private var allReminders = ArrayList<Reminder>()
    inner class ReminderViewHolder(binding: ItemReminderBinding) : RecyclerView.ViewHolder(binding.root){
        val btnDelete = binding.btnDeleteReminder
        val title = binding.tvRemTitle
        val date = binding.tvDate
        val time = binding.tvTime
        val toggler = binding.ibToggleStatus
    }

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

    override fun getItemCount(): Int {
       return allReminders.size
    }

    fun updateList(List:ArrayList<Reminder>){
        allReminders.clear()
        allReminders.addAll(List)
        notifyDataSetChanged()
    }
}

interface IRemRVadapter{
    fun onItemClicked(reminder: Reminder)
    fun onItemChecked(reminder: Reminder)
}