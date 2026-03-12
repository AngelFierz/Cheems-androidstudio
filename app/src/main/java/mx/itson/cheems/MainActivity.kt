package mx.itson.cheems

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.itson.cheems.entities.Winner

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameOverCard = 0
    var chemsMasterCard = 0
    var cardsOpened = 0
    var isGameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRestart = findViewById<Button>(R.id.restart)
        btnRestart.setOnClickListener(this)


        //Winner().save(this, "Pedro Marmol", "UnusualToilet")
        //Winner().getAll(this)

        val btnNewWinner = findViewById<View>(R.id.btn_winner) as Button
        btnNewWinner.setOnClickListener(this)

        Toast.makeText(this, R.string.welcome, Toast.LENGTH_LONG).show()

        start()
    }

    fun hideNewWinnerBtn (){
        val btnNewWinner = findViewById<View>(R.id.btn_winner) as Button
        btnNewWinner.visibility = View.GONE
    }

    fun showNewWinnerBtn (){
        val btnNewWinner = findViewById<View>(R.id.btn_winner) as Button
        btnNewWinner.visibility = View.VISIBLE
    }

    fun start() {
        cardsOpened = 0
        isGameOver = false

        hideNewWinnerBtn()

        for (i in 1..12) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )
            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.cheems_question)
            btnCard.isEnabled = true
        }

        gameOverCard = (1..12).random()
        Log.d("Valor de la carta perdedora", "la carta perdedora es $gameOverCard")

        chemsMasterCard = (1..12).random()
        while (chemsMasterCard == gameOverCard) {
            chemsMasterCard = (1..12).random()
        }
        Log.d("Valor de la carta ganadora", "CheemsMaster se esconde en $chemsMasterCard")
    }

    fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorAdmin = applicationContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorAdmin.defaultVibrator
            vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            val vibrator = applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(1500)
        }
    }

    fun loose(card: Int) {
        isGameOver = true
        vibrate()
        Toast.makeText(this, R.string.loose, Toast.LENGTH_LONG).show()

        for (i in 1..12) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )
            if (i == gameOverCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_bad)
            } else if (i == chemsMasterCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_master)
            } else {
                btnCard.setBackgroundResource(R.drawable.cheems_ok)
            }
            btnCard.isEnabled = false
        }
    }

    fun win() {
        isGameOver = true
        vibrate()
        Toast.makeText(this, R.string.win, Toast.LENGTH_LONG).show()

        for (i in 1..12) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )
            if (i == chemsMasterCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_master)
            } else if (i == gameOverCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_bad)
            } else {
                btnCard.setBackgroundResource(R.drawable.cheems_ok)
            }
            btnCard.isEnabled = false
        }
        showNewWinnerBtn()
    }

    fun winCheems(card: Int) {
        isGameOver = true
        vibrate()
        Toast.makeText(this, R.string.foundCheemsMaster, Toast.LENGTH_LONG).show()

        for (i in 1..12) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )
            if (i == chemsMasterCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_master)
            } else if (i == gameOverCard) {
                btnCard.setBackgroundResource(R.drawable.cheems_bad)
            } else {
                btnCard.setBackgroundResource(R.drawable.cheems_ok)
            }
            btnCard.isEnabled = false
        }
        showNewWinnerBtn()
    }

    fun flip(card: Int) {
        if (isGameOver) return

        if (card == gameOverCard) {
            loose(card)
        } else if (card == chemsMasterCard) {
            winCheems(card)
        } else {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$card", "id", this.packageName)
            )
            if (btnCard.isEnabled) {
                btnCard.setBackgroundResource(R.drawable.cheems_ok)
                btnCard.isEnabled = false
                cardsOpened++

                if (cardsOpened == 10) {
                    win()
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.restart -> { start() }
            R.id.card1 -> flip(1)
            R.id.card2 -> flip(2)
            R.id.card3 -> flip(3)
            R.id.card4 -> flip(4)
            R.id.card5 -> flip(5)
            R.id.card6 -> flip(6)
            R.id.card7 -> flip(7)
            R.id.card8 -> flip(8)
            R.id.card9 -> flip(9)
            R.id.card10 -> flip(10)
            R.id.card11 -> flip(11)
            R.id.card12 -> flip(12)
            R.id.btn_winner -> {
                val intentWinnerForm = Intent(this, WinnerFormActivity::class.java)
                startActivity(intentWinnerForm)
            }
        }
    }
}