package com.sklagat46.quizapp
object Constants {

    const val USER_NAME: String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWERS:String="correct_answers"


    fun getQuestions(): ArrayList<Questions>{
        val questionsList = ArrayList<Questions>()
        val que1 = Questions(1,
            "What cauntry does this flag belong to?",
            R.drawable.flag_switzerland,
            "kenya",
            "USA",
            "australia",
            "switzerland",
            4
            )
        questionsList.add(que1)
        val que2 = Questions(1,
            "What cauntry does this flag belong to?",
            R.drawable.flag_seychelles,
            "sychelles",
            "USA",
            "australia",
            "switzerland",
            1
        )
        questionsList.add(que2)
        val que3 = Questions(1,
            "What cauntry does this flag belong to?",
            R.drawable.flag_yemen,
            "kenya",
            "yemen",
            "australia",
            "switzerland",
            2
        )
        questionsList.add(que3)
        val que4 = Questions(1,
            "What cauntry does this flag belong to?",
            R.drawable.austalia_flag,
            "kenya",
            "USA",
            "australia",
            "switzerland",
            3
        )
        questionsList.add(que4)
        return questionsList
    }
}