package mx.itson.cheems

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameOverCard = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        start()
    }
    fun start(){
        for (i in 1 .. 9) {
            val btnCard = findViewById<View>(
                resources.getIdentifier("card$i", "id", this.packageName)


            ) as ImageButton
            btnCard.setOnClickListener ( this )
            btnCard.setBackgroundResource(R.drawable.cheems_question)

        }

        gameOverCard = (1..9).random()

        Log.d("Valor de la carta perdedora", "la carta perdedora es ${gameOverCard.toString()}")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}