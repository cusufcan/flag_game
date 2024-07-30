package com.cusufcan.flaggame.fragment.title

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cusufcan.flaggame.R
import com.cusufcan.flaggame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerListeners(view)
    }

    private fun registerListeners(view: View) {
        binding.startBtn.setOnClickListener {
            if (binding.etName.text != null && binding.etName.text.toString().trim().isNotEmpty()) {
                val action = TitleFragmentDirections.actionTitleFragmentToGameFragment(
                    binding.etName.text.toString().trim()
                )
                Navigation.findNavController(view).navigate(action)
            } else {
                Toast.makeText(requireContext(), "Please enter your name", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}