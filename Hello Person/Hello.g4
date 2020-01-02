grammar Hello;
init : ('Hello' + nome)+ |EOF;
nome : ID | 0[1-9]+;
ID: ([a-z]|[A-Z])+;
WS  : [ \t\r\n]+ -> skip ;
