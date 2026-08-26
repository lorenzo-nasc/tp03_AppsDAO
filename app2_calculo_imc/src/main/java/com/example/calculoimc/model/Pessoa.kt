package com.example.calculoimc.model

/**
 * Classe de modelo que representa a pessoa avaliada.
 * O cálculo do IMC fica aqui, junto dos dados a que ele pertence.
 */
class Pessoa(
    var peso: Double = 0.0,
    var altura: Double = 0.0
) {

    /** IMC = peso / (altura * altura) */
    fun calcularImc(): Double {
        if (altura <= 0.0) return 0.0
        return peso / (altura * altura)
    }

    /** Faixa correspondente ao IMC, segundo a classificação da OMS. */
    fun classificacao(): String {
        val imc = calcularImc()
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso ideal"
            imc < 30.0 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}
