package com.example.gathr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gathr.databinding.ItemNoteBinding
import com.example.gathr.entities.Note
import com.example.gathr.utils.palleteColor


// A traditional recyclerview for displaying the notes stored in roomDB
//getting context and listener from activity
class NotesRVadapter(val context: Context, val listener: INotesRVadapter):RecyclerView.Adapter<NotesRVadapter.NotesViewHolder>() {

    // array to get all the notes
    private val allNotes = ArrayList<Note>()

    // array to get all the color to be applied on background
    private var colorList = ArrayList<Int>()

    inner class NotesViewHolder(binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root){
        //using view binding instead to viewholder
        val title = binding.tvTitle
        val content = binding.tvDesc
        val btnDelete = binding.btnDeleteNote
        val cv = binding.cardView
    }

    //members function
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        var binding: ItemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(context), parent , false)
        val viewHolder = NotesViewHolder(binding)
        viewHolder.btnDelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    //members function
    //set the content to array and card ids
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.title.text = currentNote.title
        holder.content.text = currentNote.text
        colorList = palleteColor.fillList()
        holder.cv.setCardBackgroundColor(ContextCompat.getColor(context, colorList[currentNote.id % 5]))
    }

    //members function
    override fun getItemCount(): Int {
        return allNotes.size
    }

    //update the list on deletion or addition
    fun updateList(List: ArrayList<Note>){
        allNotes.clear()
        allNotes.addAll(List)
        notifyDataSetChanged()
    }
}


//Making an interface listening to clicks on adapters icons
interface INotesRVadapter{
    fun onItemClicked(note: Note)
}