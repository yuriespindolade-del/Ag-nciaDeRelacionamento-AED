AGENCIA DE RELACIONAMENTO - GUIA DE USO
=======================================

REQUISITOS=====================================================================
 AGENCIA DE RELACIONAMENTO - LEIAME
 Lab. AED I - Engenharia da Computacao - CEFET-MG Campus Timoteo
 Trabalho 1
=====================================================================

1. DESCRICAO
---------------------------------------------------------------------
Ferramenta de linha de comando (interface textual) que identifica
potenciais pares entre os candidatos cadastrados em uma agencia de
relacionamento. Dois candidatos sao potenciais companheiros quando:

  a) residem no mesmo estado;
  b) atendem a opcao sexual um do outro (F, M ou I = indiferente);
  c) tem grau de interesse em comum maior ou igual ao grau desejado.

O grau de interesse em comum e a soma dos pesos das areas de
interesse que os dois candidatos tem em comum:

  Peso 1: 1-ESPORTES, 3-MUSICA, 4-CINEMA
  Peso 2: 2-ARTES, 6-ANIMAIS, 7-GASTRONOMIA
  Peso 3: 5-TECNOLOGIA, 8-CIENCIAS

O grau desejado pode ser alterado pelo usuario (valores de 1 a 15).
O grau inicial e 1.


2. REQUISITOS
---------------------------------------------------------------------
- Java (JDK) 8 ou superior instalado (comandos javac e java).
- Nenhuma biblioteca externa e necessaria.


3. ARQUIVOS DO PROGRAMA (pasta Main)
---------------------------------------------------------------------
Main.java                 Programa principal (menu interativo).
Candidato.java            TAD Candidato.
ListaIdentificadores.java TAD ListaIdentificadores.
ListaEncadeada.java       Lista simplesmente encadeada com tail e
                          sentinela (usada pelos TADs).
ListaCandidatos.java      Lista de candidatos (resultados e cadastro).
GrauInteresse.java        Pesos das areas e calculo do grau em comum.
LerCandidatos.java        Leitura do arquivo candidatos.txt.
ControleAgencia.java      Regras da aplicacao (as 6 tarefas).
GeradorCandidatos.java    Programa auxiliar: gera o candidatos.txt.


4. COMPILACAO
---------------------------------------------------------------------
Abra um terminal dentro da pasta Main e execute:

    javac *.java


5. EXECUCAO DO PROGRAMA PRINCIPAL
---------------------------------------------------------------------
Ainda dentro da pasta Main:

    java Main

- Se o arquivo candidatos.txt NAO existir, o programa gera
  automaticamente um com 30 candidatos aleatorios.
- Se o arquivo candidatos.txt JA existir, ele e apenas lido e
  nunca e sobrescrito.

Ao iniciar, o programa mostra quantos candidatos foram carregados e
o menu abaixo. Digite o numero da opcao e pressione ENTER.

  1 - Inserir/atualizar grau de interesse desejado
      Informe o novo grau (1 a 15). Os potenciais companheiros sao
      recalculados.

  2 - Numero e lista de potenciais companheiros de um candidato
      Informe o id ou o nome do candidato. Sao exibidos o numero e
      os nomes dos potenciais companheiros.

  3 - Ranking de compatibilidade
      Informe K. Sao exibidos id/nome dos K candidatos com maior
      numero de potenciais companheiros.

  4 - Candidatos sem nenhum companheiro
      Exibe id/nome dos candidatos sem nenhum potencial companheiro
      (para o grau desejado atual).

  5 - Candidatos com interesse em uma area

- Java (JDK) 8 ou superior.

COMPILAR (dentro da pasta Main)
    javac *.java

EXECUTAR
    java Main

- Se candidatos.txt nao existir, o programa gera um com 30 candidatos.
- Se ja existir, ele e apenas lido (nunca sobrescrito).

MENU (digite o numero e pressione ENTER)
  1 - Inserir/atualizar o grau de interesse desejado (1 a 15)
  2 - Numero e lista de potenciais companheiros de um candidato (id ou nome)
  3 - Ranking de compatibilidade: K candidatos com mais potenciais companheiros
  4 - Candidatos sem nenhum companheiro
  5 - Candidatos com interesse em uma area (codigo de 1 a 8)
  6 - Candidatos que residem em um estado (sigla, ex.: MG)
  0 - Sair

GERADOR DE CANDIDATOS (programa auxiliar)
    java GeradorCandidatos [quantidade] [nomes.txt] [saida.txt]

- Parametros opcionais (padrao: 30, nomes.txt, candidatos.txt).
- Sobrescreve o arquivo de saida se ele ja existir.

FORMATO DO candidatos.txt
  N (primeira linha), e para cada candidato, precedido de "****":
  nome, sigla_estado, sexo (F/M), sexo_interesse (F/M/I),
  numero_areas, area_1, area_2, ...
