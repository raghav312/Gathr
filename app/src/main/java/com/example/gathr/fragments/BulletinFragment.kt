package com.example.gathr.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gathr.R
import com.example.gathr.ViewModel.NoteViewModel
import com.example.gathr.adapters.INotesRVadapter
import com.example.gathr.adapters.NotesRVadapter
import com.example.gathr.databinding.FragmentBulletinBinding
import com.example.gathr.entities.Note


class BulletinFragment : Fragment(), INotesRVadapter,View.OnClickListener{

    private lateinit var binding: FragmentBulletinBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBulletinBinding.inflate(layoutInflater)
        val view:View? = binding.root

        binding.rvNotes.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        var adapter = NotesRVadapter(requireContext(),this)
        binding.rvNotes.adapter =adapter

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(viewLifecycleOwner, Observer {
            if(it!= null){
                adapter.updateList(it as ArrayList<Note>)
            }
        })

        binding.fabAddNotes.setOnClickListener(this)
        return view
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.fabAddNotes -> replaceFragment(CreateNoteFragment() , false)
        }
    }

    private fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()
        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.bulletin_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()

    }

}