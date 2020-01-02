// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class TListener{//auxiliar para o testrig
public static void main(String[] args) throws Exception {
ANTLRInputStream input = new ANTLRInputStream(System.in); //Lê o input do terminal
MorseLexer lexer = new MorseLexer(input); //analizador léxico
CommonTokenStream tokens = new CommonTokenStream(lexer);//gera os tokens
MorseParser parser = new MorseParser(tokens);//cria o parser
ParseTree tree = parser.init();//cria a árvore
ParseTreeWalker walker = new ParseTreeWalker();//cria o walker da árvore
MorseString traduz = new MorseString();//cria o listener que vou aplicar na minha árvore
walker.walk(traduz,tree);//caminha pela árvore tree usando o listener traduz.

}
}
