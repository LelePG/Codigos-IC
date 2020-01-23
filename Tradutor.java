import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class Tradutor {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in); //Lê o input do terminal
        Java9Lexer lexer = new Java9Lexer(input); //analizador léxico
        CommonTokenStream tokens = new CommonTokenStream(lexer);//gera os tokens
        Java9Parser parser = new Java9Parser(tokens);//cria o parser
        ParseTree tree = parser.compilationUnit();//cria a árvore

        AtomicVisitor visitor = new AtomicVisitor();//Cria o visitor que vai percorrer a árvore
        visitor.visit(tree);//só manda ele visitar os nodos. O esquema todo de printf está na classe TraduzLoader

        ParseTreeWalker walker = new ParseTreeWalker();//cria o walker da árvore
        AtomicListener tradutor = new AtomicListener();//cria o listener que vou aplicar na minha árvore
        walker.walk(tradutor,tree);//caminha pela árvore tree usando o listener traduz.
        System.out.println("fim");
    }
}