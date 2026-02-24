package mx.itson.cheems

import android.annotation.SuppressLint
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

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameOverCard = 0
    var masterCard = 0

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



        Toast.makeText(this, "Bienvenido oullea", Toast.LENGTH_LONG).show()

        start()
    }

    @SuppressLint("DiscouragedApi")
    fun start() {
        for (i in 1..12) {
            val btnCard = findViewById<ImageButton>(
                resources.getIdentifier("card$i", "id", this.packageName)
            )

            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.cheems_question)
        }

        gameOverCard = (1..12).random()
        Log.d("Valor de la carta perdedora", "La carta perdedora es $gameOverCard")
        masterCard = (1..12).random()
        //  Log.d("Test", "blablablablab $masterCard")
        Log.d("Valor de la carta master", "La carta cool es $masterCard")

    }


    fun vibracion(ms: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // si la version del telefono es igual o mayor a Android 12 (API 31)
            val vibratorAdmin =
                applicationContext.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorAdmin.defaultVibrator
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    1500,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            val vibrator = applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(1500)
        }
    }

    fun flip(card: Int) {
        if (card == masterCard) {
            Toast.makeText(this, "Bueno tu ganas toma tu like :p", Toast.LENGTH_LONG).show()
            vibracion(1500)
            for (i in 1..12) {
                val btnCard = findViewById<ImageButton>(
                    resources.getIdentifier("card$i", "id", this.packageName)
                )
                if (i == card) {
                    btnCard.setBackgroundResource(R.drawable.cheems_master)
                } else {
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
        } else if (card == gameOverCard) {


            Toast.makeText(this, "Perdiste jajajajfjfjjj mensoo", Toast.LENGTH_LONG).show()

            for (i in 1..12) {
                val btnCard = findViewById<ImageButton>(
                    resources.getIdentifier("card$i", "id", this.packageName)
                )
                if (i == card) {
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                } else {
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
    }

}

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.restart -> {
                start()
            }
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
        }

    }
}
