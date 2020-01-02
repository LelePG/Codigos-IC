// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
public class LeArquivo {
public static void main(String[] args) throws Exception {
// cria a leitura de entrada
ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
// cria o léxico
XLexer lexer = new XLexer(input); 
// os tokens consumidos pelo léxico
CommonTokenStream tokens = new CommonTokenStream(lexer);
// parser 
XParser parser = new XParser(tokens);
String s;
//s = input.toString();//imprime o que está no arquivo
//s = parser.programa().toString();//o resultado após o parsing (como o parsing só diz se está certo ou não, não retorna nada.
System.out.println(s);
}
}
