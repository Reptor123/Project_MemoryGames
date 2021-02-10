package com.example.memorygame
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.memorygame.R.drawable.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val images: MutableList<Int> =
            mutableListOf(camel, coala, fox, lion, monkey, wolf, camel, coala, fox, lion, monkey, wolf)

        val buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12)

        val cardBack = code
        var clicked = 0
        var turnOver = false
        var lastClicked = -1
        var check = 0
        var score = 0
        val tv1: TextView = findViewById(R.id.score)
        tv1.text = score.toString()

        images.shuffle()
        for(i in 0..11){
            buttons[i].setBackgroundResource(cardBack)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(images[i])
                    buttons[i].setText(images[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--
                }

                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        check+=2
                        score+=100
                        clicked = 0
                        tv1.text = score.toString()
                        if(check == 12)
                        {
                            Handler().postDelayed(
                                {
                                    finish()
                                    startActivity(intent)
                                }, 1500
                            )
                        }
                    }
                    else if(buttons[i].text != buttons[lastClicked].text){
                        clicked = 0
                        Handler().postDelayed(
                            {
                                buttons[i].setBackgroundResource(cardBack)
                                buttons[i].text = "cardBack"
                                buttons[lastClicked].setBackgroundResource(cardBack)
                                buttons[lastClicked].text = "cardBack"
                            },
                            500
                        )
                        if(score-25 <= 0)
                            score = 0
                        else if(score-25 > 0)
                            score-=25
                        tv1.text = score.toString()
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }
            }
        }

    }
}













