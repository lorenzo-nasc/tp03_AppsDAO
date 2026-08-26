package com.example.calculoimc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculoimc.dao.PessoaDao
import java.util.Locale

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvImc = findViewById<TextView>(R.id.tvImc)
        val tvClassificacao = findViewById<TextView>(R.id.tvClassificacao)
        val tvDados = findViewById<TextView>(R.id.tvDados)
        val btnNovoCalculo = findViewById<Button>(R.id.btnNovoCalculo)

        // lê os dados gravados pela tela anterior — sem putExtra/getExtra
        val pessoa = PessoaDao.retornar()

        tvImc.text = String.format(Locale.getDefault(), "%.2f", pessoa.calcularImc())
        tvClassificacao.text = pessoa.classificacao()
        tvDados.text = String.format(
            Locale.getDefault(),
            "Peso: %.1f kg  •  Altura: %.2f m",
            pessoa.peso,
            pessoa.altura
        )

        btnNovoCalculo.setOnClickListener {
            PessoaDao.limpar()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
