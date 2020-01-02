// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
public class Leitor {
public static void main(String[] args) throws Exception {
// cria a leitura de entrada
ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
// cria o léxico
Java9Lexer lexer = new Java9Lexer(input); 
// os tokens consumidos pelo léxico
CommonTokenStream tokens = new CommonTokenStream(lexer);
// parser 
Java9Parser parser = new Java9Parser(tokens);
String s;
//s = input.toString();//imprime o que está no arquivo
s = parser.compilationUnit().toString();//o resultado após o parsing (como o parsing só diz se está certo ou não, não retorna nada.
System.out.println(s);
}
}
