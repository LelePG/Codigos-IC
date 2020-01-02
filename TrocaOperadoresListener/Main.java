// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class Main{//auxiliar para o testrig
public static void main(String[] args) throws Exception {
ANTLRInputStream input = new ANTLRInputStream(System.in); //Lê o input do terminal
TrocaOperadoresLexer lexer = new TrocaOperadoresLexer(input); //analizador léxico
CommonTokenStream tokens = new CommonTokenStream(lexer);//gera os tokens
TrocaOperadoresParser parser = new TrocaOperadoresParser(tokens);//cria o parser
ParseTree tree = parser.init();//cria a árvore
ParseTreeWalker walker = new ParseTreeWalker();//cria o walker da árvore
Traduz tradutor = new Traduz();//cria o listener que vou aplicar na minha árvore
walker.walk(tradutor,tree);//caminha pela árvore tree usando o listener traduz.
}
}
