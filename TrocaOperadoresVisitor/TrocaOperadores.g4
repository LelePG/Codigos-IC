grammar TrocaOperadores;
init: numero (operador numero)+ | EOF;
operador: '+' #MAIS 
	| '-' #MENOS ;
numero: (NUM)+;
NUM: [0-9];
WS: [ \n\r\t] -> skip;
