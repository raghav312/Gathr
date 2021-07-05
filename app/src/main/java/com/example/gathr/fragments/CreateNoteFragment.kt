package com.example.gathr.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.gathr.R
import com.example.gathr.ViewModel.NoteViewModel
import com.example.gathr.databinding.FragmentCreateNoteBinding
import com.example.gathr.entities.Note
import com.google.android.material.snackbar.Snackbar
import java.util.*

class CreateNoteFragment : Fragment() {

    lateinit var binding: FragmentCreateNoteBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater)
        val view: View? = binding.root

        binding.btnDone.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                checkParameter()
            }
        })
        binding.btnBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        })
        return view
    }

    private fun checkParameter(){
        if(binding.etTitle.text.toString().isEmpty()){
            Snackbar.make(binding.root, "Please Enter Title", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        }else{
            val note: Note = Note(
                binding.etTitle.text.toString(),
                binding.etContent.text.toString()
            )
            viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
            viewModel.insertNote(note)
            Toast.makeText(requireContext(),"Added",Toast.LENGTH_SHORT).show()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}