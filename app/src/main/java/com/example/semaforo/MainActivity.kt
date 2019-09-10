package com.example.semaforo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Layout
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.TimerTask

import java.util.*

class MainActivity : AppCompatActivity() {
    //inicializo una variable con la clase handler
    lateinit var updateHandler: Handler
    var estado = -1
    var state: TextView?=null
    var background: View?=null

    //aqui se ejecutara mi funcion cada 5 segundos
    private val updateTextTask = object : Runnable {
        override fun run() {
            prueba()//funcion para cambiar colores
            updateHandler.postDelayed(this, 5000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //le devuelve un bucle
        updateHandler = Handler(Looper.getMainLooper())

        //id del layout
         background= findViewById<LinearLayout>(R.id.backgroundLayout)

        //envento onclcick del BOTON para iniciar el semaforo
        val btn = findViewById<Button>(R.id.boton)
        btn.setOnClickListener {view->

            if (estado == -1){
                //ejecuta el la funcion
                updateHandler.post(updateTextTask)
                estado = 0
                btn.setText("parar")

            }else{
                //detiene la funcion
                updateHandler.removeCallbacks(updateTextTask)
                estado = -1
                state!!.setText("Deshabilitado")
                btn.setText("Iniciar")
                background!!.setBackgroundColor(Color.GRAY)
            }


        }


    }
    //FUNCION PARA CAMBIAR LOS COLORES DEL FONDO CON WHEN
    private fun prueba(){
        //guarda el id del layout
        //val background = findViewById<LinearLayout>(R.id.backgroundLayout)
        //Guarda el id para setear el textview a los diferentes estados
        state= findViewById<TextView>(R.id.textview)
        when (estado){
            0 ->{

                state!!.setText("Alto")
                background!!.setBackgroundColor(Color.RED)
                estado += 1
            }
            1 ->{
                state!!.setText("Precaucion")
                background!!.setBackgroundColor(Color.YELLOW)
                estado += 1
            }
            2 ->{
                state!!.setText("Adelante")
                background!!.setBackgroundColor(Color.GREEN)
                estado = 0
            }
        }
    }
}
