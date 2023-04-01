package com.example.eestech_challenge_begemol.Screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Adapter.CategoryAdapter
import com.example.eestech_challenge_begemol.Data.CategoryViewModel
import com.example.eestech_challenge_begemol.Data.UserViewModel
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.Model.Task
import com.example.eestech_challenge_begemol.Model.User
import com.example.eestech_challenge_begemol.R
import com.example.eestech_challenge_begemol.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter
    private var recyclerView: RecyclerView? = null
    private var categoryList: List<Category> = arrayListOf()

    private val viewModel: CategoryViewModel by activityViewModels()

    private val viewModelUser: UserViewModel by activityViewModels()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCategories()
        getUserData()
        binding.progressBar.max = 24
        binding.progressBar.progress = 4
        recyclerView = binding.taskList
        adapter = CategoryAdapter(listener, categoryList)
        recyclerView!!.adapter = adapter

        binding.baseKnowledgeButton.setOnClickListener {
            recyclerView!!.adapter = CategoryAdapter(listener, arrayListOf())
            binding.titleCat.text = "База знаний"
            binding.baseKnowledgeButton.setBackgroundColor(resources.getColor(R.color.black))
            binding.baseKnowledgeButton.setTextColor(resources.getColor(R.color.white))
            binding.taskButton.setBackgroundColor(resources.getColor(R.color.white))
            binding.taskButton.setTextColor(resources.getColor(R.color.black))
        }
        binding.taskButton.setOnClickListener {
            recyclerView!!.adapter = adapter
            binding.titleCat.text = "Задания"
            binding.baseKnowledgeButton.setBackgroundColor(resources.getColor(R.color.white))
            binding.baseKnowledgeButton.setTextColor(resources.getColor(R.color.black))
            binding.taskButton.setBackgroundColor(resources.getColor(R.color.black))
            binding.taskButton.setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun getUserData() {
        viewModelUser.getUser().observe(viewLifecycleOwner) { data ->
            user = data
            binding.progressBar.progress = user.currentScore!!
            binding.progressBar.max = user.dailyScore!!
            binding.dailyScore.text = user.currentScore!!.toString() + "/" + user.dailyScore!!.toString()
        }
    }

    private fun getCategories() {
        viewModel.getCharacterList().observe(viewLifecycleOwner) { data ->
            categoryList = data
            adapter.updateList(categoryList)
        }
    }

    private val listener = CategoryAdapter.OnClickListener { id ->
        findNavController().navigate(
            MainFragmentDirections.actionMainScreenToTaskScreen(id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}