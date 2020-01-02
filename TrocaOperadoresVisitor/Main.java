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
TraduzVisitor visitor = new TraduzVisitor();//Cria o visitor que vai percorrer a árvore
visitor.visit(tree);//só manda ele visitar os nodos. O esquema todo de printf está na classe TraduzLoader
System.out.println();//só pro comando do terminal ir para a próxima linha.
}
}
