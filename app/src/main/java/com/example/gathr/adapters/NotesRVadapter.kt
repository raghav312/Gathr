package com.example.gathr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gathr.databinding.ItemNoteBinding
import com.example.gathr.entities.Note
import com.example.gathr.utils.palleteColor

class NotesRVadapter(val context: Context, val listener: INotesRVadapter):RecyclerView.Adapter<NotesRVadapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Note>()
    private var colorList = ArrayList<Int>()

    inner class NotesViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.tvTitle
        val content = binding.tvDesc
        val btnDelete = binding.btnDeleteNote
        val cv = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        var binding: ItemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(context), parent , false)
        val viewHolder = NotesViewHolder(binding)
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.title.text = currentNote.title
        holder.content.text = currentNote.text
        colorList = palleteColor.fillList()
        holder.cv.setCardBackgroundColor(ContextCompat.getColor(context, colorList[currentNote.id % 5]))
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(List: ArrayList<Note>){
        allNotes.clear()
        allNotes.addAll(List)
        notifyDataSetChanged()
    }
}

interface INotesRVadapter{
    fun onItemClicked(note: Note)
}