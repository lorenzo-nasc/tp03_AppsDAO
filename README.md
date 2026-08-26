# AppsDAO — Atividade sobre DAO

Projeto Android Studio com **2 módulos**, ambos usando o padrão **DAO**
(Data Access Object / Objeto de Acesso a Dados) para levar dados de uma tela
à outra.

| Módulo (pasta) | App | Exercício |
|--------|-----|-----------|
| `app1_cadastro_alunos` | **App 1** — Cadastro de Alunos | Tela 1: nome · Tela 2: gera matrícula aleatória |
| `app2_calculo_imc` | **App 2** — Calculadora de IMC | Tela 1: peso e altura · Tela 2: IMC + classificação |

## Como abrir

1. Descompacte. Se o Windows criar `AppsDAO` **dentro** de outra `AppsDAO`,
   use a de dentro — é a que contém o `settings.gradle.kts`.
2. Android Studio → **File → Open…** → selecione essa pasta.
3. **Trust Project** se o diálogo aparecer, e aguarde o *Gradle Sync*.
4. No seletor ao lado do ▶, escolha `app1_cadastro_alunos` ou `app2_calculo_imc`.

## A estrutura do DAO

Cada módulo segue a mesma organização de pacotes pedida em aula:

```
com.example.<app>/
├── model/       <- a classe de dados (Aluno, Pessoa)
├── dao/         <- o DAO (AlunoDao, PessoaDao)
├── MainActivity.kt
└── ResultadoActivity.kt
```

### O companion object

Em Kotlin, `companion object` cria **membros estáticos** dentro de uma classe:
eles são acessados **diretamente pelo nome da classe**, sem instanciar.

```kotlin
class AlunoDao {
    companion object {
        private val aluno: Aluno = Aluno()

        fun adicionar(novo: Aluno) {
            aluno.nome = novo.nome
            aluno.matricula = novo.matricula
        }

        fun retornar(): Aluno = aluno
    }
}
```

Uso, de qualquer Activity:

```kotlin
AlunoDao.adicionar(Aluno(nome = "Maria"))   // tela 1 grava
val aluno = AlunoDao.retornar()             // tela 2 lê
```

### Por que isso importa

Na atividade anterior, os dados iam de uma tela para outra por
`putExtra` / `getStringExtra` na Intent. Com o DAO, a `Intent` só **abre** a
segunda tela — os dados ficam guardados no objeto estático e cada tela conversa
com o DAO, não com a outra tela.

A vantagem aparece quando há mais telas ou mais campos: em vez de repassar tudo
de tela em tela, todas leem da mesma fonte. É esse o papel de um Objeto de Acesso
a Dados — centralizar o acesso, escondendo de quem usa **onde** o dado está
guardado. Num app real, esse mesmo DAO poderia gravar num banco SQLite ou numa
API sem que as Activities precisassem mudar uma linha.

> **Limite desta versão:** por ser memória estática, os dados se perdem quando o
> app é fechado. É o esperado para o exercício — persistência de verdade viria
> com Room/SQLite.

## App 1 — Cadastro de Alunos

- **Tela 1** grava o nome com `AlunoDao.adicionar(Aluno(nome = nome))`.
- **Tela 2** lê com `AlunoDao.retornar()` e, no botão, gera a matrícula:

```kotlin
val matricula = Random.nextInt(100000, 1000000)  // 6 dígitos
AlunoDao.adicionarMatricula(matricula)
```

A matrícula volta para o DAO antes de ser exibida — então a tela mostra o que
está guardado, não uma variável solta.

## App 2 — Calculadora de IMC

- **Tela 1** grava peso e altura com `PessoaDao.adicionar(...)`.
- **Tela 2** lê com `PessoaDao.retornar()` e chama os métodos do modelo.

O cálculo mora na classe `Pessoa`, junto dos dados a que pertence:

```kotlin
fun calcularImc(): Double = peso / (altura * altura)
```

Faixas usadas (classificação da OMS):

| IMC | Classificação |
|-----|---------------|
| menor que 18,5 | Abaixo do peso |
| 18,5 a 24,9 | Peso ideal |
| 25,0 a 29,9 | Sobrepeso |
| 30,0 ou mais | Obesidade |

Teste sugerido: peso `70`, altura `1.75` → IMC `22,86` → Peso ideal.
Os campos aceitam vírgula ou ponto como separador decimal.

## Configuração

- Gradle 8.9 · AGP 8.7.3 · Kotlin 2.0.21
- `compileSdk` / `targetSdk` = 35 · `minSdk` = 24 · Java 17
- Views + XML, `AppCompatActivity` e `findViewById`
