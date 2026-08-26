package com.example.calculoimc.dao

import com.example.calculoimc.model.Pessoa

/**
 * DAO - Data Access Object (Objeto de Acesso a Dados).
 *
 * O companion object cria membros estáticos dentro da classe, acessíveis
 * diretamente pelo nome da classe (PessoaDao.adicionar(...)), sem instanciar.
 *
 * Assim a tela de resultado lê os dados que a tela de entrada gravou,
 * sem precisar passar nada pela Intent.
 */
class PessoaDao {

    companion object {

        // instância única mantida pelo DAO
        private val pessoa: Pessoa = Pessoa()

        /** Grava peso e altura no DAO. */
        fun adicionar(nova: Pessoa) {
            pessoa.peso = nova.peso
            pessoa.altura = nova.altura
        }

        /** Devolve a pessoa armazenada. */
        fun retornar(): Pessoa = pessoa

        /** Zera os dados para um novo cálculo. */
        fun limpar() {
            pessoa.peso = 0.0
            pessoa.altura = 0.0
        }
    }
}
