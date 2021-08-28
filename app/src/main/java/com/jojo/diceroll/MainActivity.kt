package com.jojo.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFab: View = findViewById(R.id.FirstFab)
        val secondFab: View = findViewById(R.id.SecondFab)
        val diceImage: ImageView = findViewById(R.id.imageView)
        val editText = findViewById<EditText>(R.id.editValue)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener {
            if(editText.visibility == View.VISIBLE) {
                rollCustom()
            } else {
                rollDice()
            }

        }

        rollDice()

        firstFab.setOnClickListener{
            diceImage.visibility = View.INVISIBLE
            editText.visibility = View.VISIBLE
            findViewById<TextView>(R.id.resultText).visibility = View.VISIBLE
            findViewById<TextView>(R.id.numberIs).visibility = View.VISIBLE
            findViewById<TextView>(R.id.numEditHint).visibility = View.VISIBLE
        }
        secondFab.setOnClickListener{
            editText.visibility = View.INVISIBLE
            diceImage.visibility = View.VISIBLE
            findViewById<TextView>(R.id.resultText).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.numberIs).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.numEditHint).visibility = View.INVISIBLE
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val diceImage: ImageView = findViewById(R.id.imageView)
        when (diceRoll) {
            1 -> diceImage.setImageResource(R.drawable.dice_1)
            2 -> diceImage.setImageResource(R.drawable.dice_2)
            3 -> diceImage.setImageResource(R.drawable.dice_3)
            4 -> diceImage.setImageResource(R.drawable.dice_4)
            5 -> diceImage.setImageResource(R.drawable.dice_5)
            6 -> diceImage.setImageResource(R.drawable.dice_6)
        }
        diceImage.contentDescription = diceRoll.toString()
    }

    private fun rollCustom(){
        val customNum = findViewById<EditText>(R.id.editValue).text
        val dice = Dice(customNum.toString().toInt())
        val diceRoll = dice.roll()
        val resultText: TextView = findViewById(R.id.resultText)
        resultText.text = diceRoll.toString()
    }

}

class Dice(private val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}