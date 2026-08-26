package com.example.calculoimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.calculoimc.dao.PessoaDao
import com.example.calculoimc.model.Pessoa

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etPeso = findViewById<EditText>(R.id.etPeso)
        val etAltura = findViewById<EditText>(R.id.etAltura)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        // aceita tanto "1,75" quanto "1.75"
        fun EditText.valor(): Double? =
            text.toString().trim().replace(",", ".").toDoubleOrNull()

        btnCalcular.setOnClickListener {
            val peso = etPeso.valor()
            val altura = etAltura.valor()

            when {
                peso == null || peso <= 0 -> {
                    etPeso.error = "Informe um peso válido"
                    etPeso.requestFocus()
                }

                altura == null || altura <= 0 -> {
                    etAltura.error = "Informe uma altura válida"
                    etAltura.requestFocus()
                }

                else -> {
                    // grava no DAO — a tela de resultado vai ler daqui
                    PessoaDao.adicionar(Pessoa(peso = peso, altura = altura))

                    startActivity(Intent(this, ResultadoActivity::class.java))
                }
            }
        }
    }
}
