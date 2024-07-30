package com.cusufcan.flaggame.fragment.game

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cusufcan.flaggame.R
import com.cusufcan.flaggame.databinding.FragmentGameBinding
import com.cusufcan.flaggame.model.Question
import com.cusufcan.flaggame.util.Constants

class GameFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentGameBinding

    private lateinit var mQuestionList: ArrayList<Question>

    private var mSelectedPos = 0
    private var mCurrentPos = 1
    private var mCorrectAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mQuestionList = Constants.getQuestion()

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        setQuestion()
    }

    private fun setQuestion() {
        val question = mQuestionList[mCurrentPos - 1]

        binding.tvQuestion.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        binding.pb.progress = mCurrentPos
        binding.tvProgress.text = "$mCurrentPos/${binding.pb.max}"

        defaultAppearance()

        if (mCurrentPos == mQuestionList.size) {
            binding.btnSubmit.text = "Quiz Finished"
        } else {
            binding.btnSubmit.text = "Submit"
        }
    }

    private fun defaultAppearance() {
        val options = arrayListOf(
            binding.tvOptionOne,
            binding.tvOptionTwo,
            binding.tvOptionThree,
            binding.tvOptionFour,
        )

        options.forEach {
            it.setTextColor(Color.parseColor("#7A8089"))
            it.typeface = Typeface.DEFAULT
            it.background = context?.let { mContext ->
                ContextCompat.getDrawable(
                    mContext, R.drawable.default_option_border_bg
                )
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_optionOne -> selectedOptionView(binding.tvOptionOne, 1)

            R.id.tv_optionTwo -> selectedOptionView(binding.tvOptionTwo, 2)

            R.id.tv_optionThree -> selectedOptionView(binding.tvOptionThree, 3)

            R.id.tv_optionFour -> selectedOptionView(binding.tvOptionFour, 4)

            R.id.btnSubmit -> {
                if (mSelectedPos == 0) {
                    mCurrentPos++

                    when {
                        mCurrentPos <= mQuestionList.size -> {
                            setQuestion()
                        }

                        else -> {
                            val action = GameFragmentDirections.actionGameFragmentToScoreFragment(
                                mCorrectAnswer,
                                GameFragmentArgs.fromBundle(requireArguments()).name,
                            )
                            Navigation.findNavController(v).navigate(action)
                        }
                    }
                } else {
                    val question = mQuestionList[mCurrentPos - 1]

                    if (question.correctAnswer != mSelectedPos) {
                        answerView(mSelectedPos, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswer++
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        if (mCurrentPos == mQuestionList.size) {
                            binding.btnSubmit.text = "Finished"
                        } else {
                            binding.btnSubmit.text = "Next Question"
                        }
                    }

                    mSelectedPos = 0
                }
            }
        }
    }

    private fun selectedOptionView(textView: TextView, pos: Int) {
        defaultAppearance()
        mSelectedPos = pos

        textView.setTextColor(Color.parseColor("#363A43"))

        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_border_bg)
    }

    private fun answerView(mSelectedPos: Int, borderBg: Int) {
        when (mSelectedPos) {
            1 -> binding.tvOptionOne.background =
                ContextCompat.getDrawable(requireContext(), borderBg)

            2 -> binding.tvOptionTwo.background =
                ContextCompat.getDrawable(requireContext(), borderBg)

            3 -> binding.tvOptionThree.background =
                ContextCompat.getDrawable(requireContext(), borderBg)

            4 -> binding.tvOptionFour.background =
                ContextCompat.getDrawable(requireContext(), borderBg)
        }
    }
}