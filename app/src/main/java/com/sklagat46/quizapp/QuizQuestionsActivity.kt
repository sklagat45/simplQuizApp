package com.sklagat46.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener {

    private var mCurrentPosition:Int = 1//default and the first quiz
    private var mQuestionsList:ArrayList<Questions>? =null
    private var mSelectedOptionPosition:Int =0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        mQuestionsList =Constants.getQuestions()
        setQuestions()
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,  1)
            }
            R.id.tv_option_two ->{
                selectedOptionView(tv_option_two, 2)
            }
            R.id.tv_option_three ->{
                selectedOptionView(tv_option_three, 3)
            }
            R.id.tv_option_four ->{
                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit->{

                if (mSelectedOptionPosition==0){
                    mCurrentPosition++
                    when{mCurrentPosition<=mQuestionsList!!.size->{
                        setQuestions()
                    }else->{
                        val intent =
                            Intent(this@QuizQuestionsActivity, ResultsActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                        startActivity(intent)
                        finish()
                    }
                    }
                }else{
                    val questions=mQuestionsList?.get(mCurrentPosition-1)
                    if (questions!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(questions.correctAnswer, R.drawable.correct_option_border_bg)
                    if (mCurrentPosition==mQuestionsList!!.size){
                        btn_submit.text = "Finish"
                    }else{
                        btn_submit.text = "Go to the next Question"
                    }
                    mSelectedOptionPosition=0

                }
            }
        }

    }
    private fun setQuestions(){
        val questions = mQuestionsList!![mCurrentPosition-1]
        defaultOptionView()

        if (mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text= "Finish"
        }else{
            btn_submit.text= "SUBMIT"
        }
        progressBar.progress=mCurrentPosition
        tv_progress.text ="$mCurrentPosition"+"/"+ progressBar.max

        tv_question.text = questions.question
        tv_option_one.text =questions!!.question
        iv_image.setImageResource(questions.image)
        tv_option_one.text= questions.optionOne
        tv_option_two.text= questions.optionTwo
        tv_option_three.text= questions.optionThree
        tv_option_four.text= questions.optionFour
    }
    private fun selectedOptionView(tv:TextView, selectedOptionNum:Int){

        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg)

    }
    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
    private fun answerView(answer:Int, drawableView:Int){
        when (answer){
            1->{
                tv_option_one.background =ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,drawableView)
            }
            2->{
                tv_option_two.background =ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,drawableView)
            }
            3->{
                tv_option_three.background =ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,drawableView)
            }
            4->{
                tv_option_four.background =ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,drawableView)
            }
        }
    }
}
