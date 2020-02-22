import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.io.*;//Pra fazer a leitura do arquivo

public class Tradutor {

    static TokenStreamRewriter rewriter;//variável onde vou armazenar todos meus tokens, e também é a variável que será modificada
    //pelo meu listener

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0])); //Lê o input de um arquivo passado como parametro
        Java9Lexer lexer = new Java9Lexer(input); //analizador léxico
        CommonTokenStream tokens = new CommonTokenStream(lexer);//gera os tokens
        Java9Parser parser = new Java9Parser(tokens);//cria o parser
        ParseTree tree = parser.compilationUnit();//cria a árvore

        //VISITOR
        //AtomicVisitor visitor = new AtomicVisitor();//Cria o visitor que vai percorrer a árvore
        //visitor.visit(tree);//só manda ele visitar os nodos. O esquema todo de printf está na classe TraduzLoader

        //LISTENER
        ParseTreeWalker walker = new ParseTreeWalker();//cria o walker da árvore
        AtomicListener tradutor = new AtomicListener();//cria o listener que vou aplicar na minha árvore
        //walker.walk(tradutor,tree);//caminha pela árvore tree usando o listener tradutor, preciso definir o TokenStreamRewriter primeiro pra poder caminhar

        //TOKEN STREAM REWRITER
        rewriter = new TokenStreamRewriter(tokens);//inicializa a variável com o CommonTokenStrem ali de cima
        //parser.compilationUnit();//desnecessário
        walker.walk(tradutor, tree);//caminha pela àrvore, modificando o que tem que ser modificado.
        System.out.println(rewriter.getText());//imprime o resultado final
    }

}