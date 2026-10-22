AGENCIA DE RELACIONAMENTO - GUIA DE USO
=======================================

REQUISITOS
- Java (JDK) 8 ou superior.

COMPILAR (dentro da pasta Main)
    javac *.java

EXECUTAR
    java Main

- A cada execucao, o programa gera um novo candidatos.txt com 30
  candidatos aleatorios (sobrescrevendo o anterior) e o carrega.

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
