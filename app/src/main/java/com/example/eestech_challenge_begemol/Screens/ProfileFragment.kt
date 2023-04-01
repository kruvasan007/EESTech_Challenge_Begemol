package com.example.eestech_challenge_begemol.Screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.eestech_challenge_begemol.Adapter.CategoryAdapter
import com.example.eestech_challenge_begemol.Data.Repository
import com.example.eestech_challenge_begemol.Data.TaskViewModel
import com.example.eestech_challenge_begemol.Data.UserViewModel
import com.example.eestech_challenge_begemol.Model.Category
import com.example.eestech_challenge_begemol.Model.User
import com.example.eestech_challenge_begemol.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment() {
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by activityViewModels()
    private lateinit var user: User
    private lateinit var adapter: CategoryAdapter
    private var recyclerView: RecyclerView? = null
    private var categoryList: List<Category> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserData()
        binding.changeButton.setOnClickListener {
            binding.changeButton.visibility = View.GONE
            binding.slider.visibility = View.VISIBLE
            binding.saveButton.visibility = View.VISIBLE
        }
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            binding.dayPlanCount.text = "" + user.currentScore + "/" + value.toInt()
        }
        binding.saveButton.setOnClickListener {
            binding.slider.visibility = View.GONE
            binding.saveButton.visibility = View.GONE
            binding.changeButton.visibility = View.VISIBLE
        }
    }

    private fun getUserData() {
        viewModel.getUser().observe(viewLifecycleOwner) { data ->
            user = data
            binding.progressBarExp.max = user.experienceMax!!
            binding.progressBarExp.progress = user.experience!!
            binding.progressBarTask.progress = user.currentScore!!
            binding.progressBarTask.max = user.dailyScore!!
            binding.dayPlanCount.text =
                user.currentScore.toString() + "/" + user.dailyScore.toString()
            binding.expTitle.text = user.level.toString() + " уровень"
            binding.maxExp.text = user.experienceMax.toString() + "xp"
            binding.minExp.text = user.experience.toString() + "xp"
        }
    }
}