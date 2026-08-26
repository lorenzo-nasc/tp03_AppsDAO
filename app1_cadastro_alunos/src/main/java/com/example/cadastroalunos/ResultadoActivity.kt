package com.example.cadastroalunos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cadastroalunos.dao.AlunoDao
import kotlin.random.Random

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvNome = findViewById<TextView>(R.id.tvNome)
        val tvMatricula = findViewById<TextView>(R.id.tvMatricula)
        val tvResumo = findViewById<TextView>(R.id.tvResumo)
        val btnGerarMatricula = findViewById<Button>(R.id.btnGerarMatricula)
        val btnNovoCadastro = findViewById<Button>(R.id.btnNovoCadastro)

        // lê o aluno gravado pela tela anterior — sem putExtra/getExtra
        val aluno = AlunoDao.retornar()
        tvNome.text = aluno.nome

        btnGerarMatricula.setOnClickListener {
            // matrícula aleatória de 6 dígitos
            val matricula = Random.nextInt(100000, 1000000)

            // atualiza o DAO com a matrícula gerada
            AlunoDao.adicionarMatricula(matricula)

            val atualizado = AlunoDao.retornar()
            tvMatricula.text = atualizado.matricula.toString()
            tvResumo.text =
                "Aluno ${atualizado.nome} matriculado sob o número ${atualizado.matricula}."
        }

        btnNovoCadastro.setOnClickListener {
            AlunoDao.limpar()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
