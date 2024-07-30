package com.cusufcan.flaggame.util

import com.cusufcan.flaggame.R
import com.cusufcan.flaggame.model.Question

object Constants {
    fun getQuestion(): ArrayList<Question> {
        val questionList = arrayListOf(
            Question(
                1,
                "What country flag is this?",
                R.drawable.australia,
                "Australia",
                "Argentina",
                "Armenia",
                "USA",
                1
            ),
            Question(
                2,
                "What country flag is this",
                R.drawable.mexico,
                "Australia",
                "Argentina",
                "Mexico",
                "USA",
                3
            ),
            Question(
                3,
                "What country flag is this",
                R.drawable.france,
                "Mexico",
                "France",
                "Africa",
                "USA",
                2
            ),
            Question(
                4,
                "What country flag is this",
                R.drawable.turkey,
                "Kazakistan",
                "Ukraine",
                "Turkey",
                "USA",
                3
            ),
            Question(
                5,
                "What country flag is this",
                R.drawable.us,
                "USA",
                "Argentina",
                "Armenia",
                "South America",
                1
            ),
            Question(
                6,
                "What country flag is this",
                R.drawable.uk,
                "Australia",
                "Argentina",
                "UK",
                "USA",
                3
            ),
            Question(
                7,
                "What country flag is this",
                R.drawable.european,
                "Scotland",
                "European Union",
                "Armenia",
                "USA",
                2
            ),
            Question(
                8,
                "What country flag is this",
                R.drawable.germany,
                "Netherlands",
                "Spain",
                "Belgium",
                "Germany",
                4
            ),
            Question(
                9,
                "What country flag is this",
                R.drawable.canada,
                "Denmark",
                "Argentina",
                "Canada",
                "USA",
                3
            ),
            Question(
                10,
                "What country flag is this",
                R.drawable.india,
                "India",
                "Iran",
                "Ireland",
                "USA",
                1
            ),
        )
        return questionList
    }
}