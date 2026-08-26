package com.example.cadastroalunos.dao

import com.example.cadastroalunos.model.Aluno

/**
 * DAO - Data Access Object (Objeto de Acesso a Dados).
 *
 * O companion object cria membros estáticos dentro da classe: eles podem ser
 * acessados diretamente pelo nome da classe (AlunoDao.adicionar(...)), sem
 * precisar instanciar o DAO.
 *
 * Como o objeto guardado aqui é estático, ele continua vivo enquanto o app
 * estiver aberto — por isso a segunda tela consegue ler o que a primeira gravou,
 * sem usar putExtra/getExtra na Intent.
 */
class AlunoDao {

    companion object {

        // instância única mantida pelo DAO
        private val aluno: Aluno = Aluno()

        /** Grava os dados do aluno no DAO. */
        fun adicionar(novo: Aluno) {
            aluno.nome = novo.nome
            aluno.matricula = novo.matricula
        }

        /** Grava apenas a matrícula, mantendo o nome já cadastrado. */
        fun adicionarMatricula(matricula: Int) {
            aluno.matricula = matricula
        }

        /** Devolve o aluno armazenado. */
        fun retornar(): Aluno = aluno

        /** Limpa os dados para um novo cadastro. */
        fun limpar() {
            aluno.nome = ""
            aluno.matricula = 0
        }
    }
}
