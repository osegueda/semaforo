package com.example.semaforo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.TimerTask

import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var updateHandler: Handler
    var estado = -1


    private val updateTextTask = object : Runnable {
        override fun run() {
            prueba()
            updateHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateHandler = Handler(Looper.getMainLooper())

         val background1= findViewById<LinearLayout>(R.id.backgroundLayout)

        val btn = findViewById<Button>(R.id.boton)
        btn.setOnClickListener {view->

            if (estado == -1){
                updateHandler.post(updateTextTask)
                estado = 0
                btn.setText("parar")

            }else{
                updateHandler.removeCallbacks(updateTextTask)
                estado = -1
                btn.setText("Iniciar")
                background1.setBackgroundColor(Color.GRAY)
            }


        }


    }

    private fun prueba(){
        val background = findViewById<LinearLayout>(R.id.backgroundLayout)
        val state= findViewById<TextView>(R.id.textview)
        when (estado){
            0 ->{
                state.setText("Adelante")
                background.setBackgroundColor(Color.GREEN)
                estado += 1
            }
            1 ->{
                state.setText("Precaucion")
                background.setBackgroundColor(Color.YELLOW)
                estado += 1
            }
            2 ->{
                state.setText("Alto")
                background.setBackgroundColor(Color.RED)
                estado = 0
            }
        }
    }
}
