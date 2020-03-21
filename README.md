## IMPLEMENTAÇÃO DE UMA INTERFACE ﻿DE ALTO NÍVEL PARA MEMÓRIAS TRANSACIONAIS DISTRIBUÍDAS USANDO RMI EM JAVA

Projeto incial da interface proposta desenvolvido de agosto de 2019 até março de 2020. Foi realizada uma alteração na gramática do Java adicionando uma palavra chave atomic, que é traduzida em trechos de código repetitivos, cuja eliminação é o objetivo da interface proposta.

#### ANTLR 4
Para a implementação, foi utilizado um plugin da ferramenta ANTLR 4 para a IDE intelliJ idea, onde todo o código foi criado. As alterações do código são feitas por meio de alterações na stream de tokens de entrada, percorrendo a árvore sintática do programa passado como entrada e realizando as alterações quando a primitiva atomic, for encontrada.

#### Execução
Para realizar a execução da gramática (que no momento atual do projeto não é necessária) é preciso ter a ferramenta ANTLR 4. Supondo que a mesma esteja no mesmo diretório da gramática a ser compilada, deve ser executado o comando `java -jar antlr-4.7.2-complete.jar`
Após esse comando, executa-se:
`javac *.java`
`java Tradutor Exemplos/(Selecione o exemplo desejado)`

### IMPORTANTE
A implementação da interface não foi finalizada.

O documento .1aabeha.txt conta com algumas anotações feitas durante o desenvolvimento, porém são anotações pessoais, e podem não ter muita utilidade ou nexo para terceiros. 
