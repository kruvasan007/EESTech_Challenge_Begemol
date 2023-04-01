package com.example.eestech_challenge_begemol.Screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Adapter.AnswerAdapter
import com.example.eestech_challenge_begemol.Adapter.TaskAdapter
import com.example.eestech_challenge_begemol.Data.QuestionViewModel
import com.example.eestech_challenge_begemol.Model.Answer
import com.example.eestech_challenge_begemol.Model.Question
import com.example.eestech_challenge_begemol.R
import com.example.eestech_challenge_begemol.databinding.SelectedFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SelectedTaskFragment : Fragment() {
    private val args: SelectedTaskFragmentArgs by navArgs()
    private var _binding: SelectedFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AnswerAdapter
    private var recyclerView: RecyclerView? = null
    private var answerList: List<Answer> = arrayListOf()
    private var questionList: List<Question> = arrayListOf()

    private var currentAnswer: Int = -1
    private var currentQuestion: Int = 0
    private var userAnswers: ArrayList<Int> = arrayListOf()

    private val viewModel: QuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SelectedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.taskList
        getQuestion()
        adapter = AnswerAdapter(listener, answerList)
        recyclerView!!.adapter = adapter

        binding.nextButton.setOnClickListener {
            userAnswers.add(currentAnswer)
            currentQuestion++
            if (currentQuestion < questionList.size) updateQuestion()
            else {
                println(userAnswers)//delete
                dialogCongratulations()
            }
            if (currentQuestion == questionList.size - 1)
                binding.nextButton.text = "Завершить"
        }
    }

    private fun dialogCongratulations() {
        MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setTitle("Новая ачивка!")
            .setIcon(resources.getDrawable(R.drawable.logo))
            .setMessage("A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made. ")
            .setNegativeButton("Закрыть") { dialog, which ->
                findNavController().navigate(R.id.mainScreen)
            }
            .setPositiveButton("Все ачивки") { dialog, which ->
                findNavController().navigate(R.id.profileFragment)
            }
            .show()
    }

    private fun updateQuestion() {
        binding.descriprion.text = questionList[currentQuestion].text
        binding.counted.text = "" + (currentQuestion + 1) + "/" + questionList.size
        answerList = questionList[currentQuestion].answers
        adapter.updateList(answerList)
    }

    private fun getQuestion() {
        viewModel.getQuestionList(args.taskId).observe(viewLifecycleOwner) { data ->
            questionList = data
            binding.descriprion.text = questionList[currentQuestion].text
            binding.counted.text = "" + (currentQuestion + 1) + "/" + questionList.size
            answerList = data[currentQuestion].answers
            adapter.updateList(answerList)
            binding.loading.visibility = View.GONE
        }
    }

    private val listener = TaskAdapter.OnClickListener { id ->
        currentAnswer = id
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}