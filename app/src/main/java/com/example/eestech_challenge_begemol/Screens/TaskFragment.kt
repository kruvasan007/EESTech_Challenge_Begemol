package com.example.eestech_challenge_begemol.Screens

import android.os.Bundle
import android.system.Os.accept
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Adapter.TaskAdapter
import com.example.eestech_challenge_begemol.Data.TaskViewModel
import com.example.eestech_challenge_begemol.Model.Task
import com.example.eestech_challenge_begemol.R
import com.example.eestech_challenge_begemol.databinding.TasksFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs

class TaskFragment : Fragment() {
    private val args: TaskFragmentArgs by navArgs()
    private var _binding: TasksFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter
    private var recyclerView: RecyclerView? = null
    private var taskList: List<Task> = arrayListOf()

    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TasksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTasks()
        recyclerView = binding.taskList
        adapter = TaskAdapter(listener, taskList)
        recyclerView!!.adapter = adapter
    }

    private fun getTasks() {
        viewModel.getTaskList(args.categoryId).observe(viewLifecycleOwner) { data ->
            taskList = data
            adapter.updateList(taskList)
            binding.loading.visibility = View.GONE
        }
    }

    private val listener = TaskAdapter.OnClickListener { id ->
        MaterialAlertDialogBuilder(requireContext(), com.google.android.material.R.style.MaterialAlertDialog_Material3)
            .setTitle("Задание " + (id + 1))
            .setMessage(taskList[id].name)
            .setNegativeButton("Назад") { dialog, which ->
                dialog.cancel()
            }
            .setPositiveButton("Начать") { dialog, which ->
                findNavController().navigate(
                    TaskFragmentDirections.actionTaskScreenToSelectedTaskFragment(id)
                )
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}