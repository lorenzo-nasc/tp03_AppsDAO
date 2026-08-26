package com.example.cadastroalunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastroalunos.dao.AlunoDao
import com.example.cadastroalunos.model.Aluno

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNome = findViewById<EditText>(R.id.etNome)
        val btnAvancar = findViewById<Button>(R.id.btnAvancar)

        btnAvancar.setOnClickListener {
            val nome = etNome.text.toString().trim()

            if (nome.isEmpty()) {
                etNome.error = "Informe o nome do aluno"
                etNome.requestFocus()
            } else {
                // grava no DAO — a segunda tela vai ler daqui
                AlunoDao.adicionar(Aluno(nome = nome))

                startActivity(Intent(this, ResultadoActivity::class.java))
                etNome.text.clear()
            }
        }
    }
}
