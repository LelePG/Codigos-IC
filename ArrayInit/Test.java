// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class Test {//auxiliar para o testrig
public static void main(String[] args) throws Exception {
// create a CharStream that reads from standard input
ANTLRInputStream input = new ANTLRInputStream(System.in);
// create a lexer that feeds off of input CharStream
ArrayInitLexer lexer = new ArrayInitLexer(input);
// create a buffer of tokens pulled from the lexer
CommonTokenStream tokens = new CommonTokenStream(lexer);
System.out.println(input.toString());
// create a parser that feeds off the tokens buffer
ArrayInitParser parser = new ArrayInitParser(tokens);//mostra só os tokens obtidos até o momento
//ParseTree tree = parser.init(); // begin parsing at init rule
//System.out.println(tree.toStringTree(parser)); // print LISP-style tree
}
}
