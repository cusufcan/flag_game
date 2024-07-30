package com.cusufcan.flaggame.fragment.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.cusufcan.flaggame.R
import com.cusufcan.flaggame.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args by navArgs<ScoreFragmentArgs>()
        binding.displayResult.text = "${args.name}! you have scored ${args.score} points out of 10"

        binding.playAgain.setOnClickListener {
            val action = ScoreFragmentDirections.actionScoreFragmentToTitleFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }
}