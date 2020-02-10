import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.misc.Interval;


public class AtomicListener extends Java9BaseListener {
    //Cada regra da gramática tem um índice. Posso descobrir esse índice usando ctx.getRuleIndex(), e printar
    //usando ctx.getText() só o que me interessa
    final int ATOMICINDEX = 6;//índice da regra do atomic
    //atomic 6
    //statement without trailing substatement 139
    //method invocation 204
    //block 132
    //int c = 0;

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        if (ctx.getRuleIndex() == ATOMICINDEX || findAtomicParent(ctx)) {
            return;//Se eu estiver na regra do atomic, ou dentro de um escopo de atomic...
       }
        else if ((ctx.getChild(0) instanceof TerminalNodeImpl || ctx.getChild(0)==null)
                && ctx.getChildCount() <= 1) {//caso contrário
            System.out.println(ctx.getStart().getText());//Funciona para todas as folhas que são identificadores, mas não funciona pros tokens
            /*int a = ctx.start.getStartIndex();
            int b = ctx.stop.getStopIndex();
            Interval interval = new Interval(a,b);
            System.out.println(ctx.start.getInputStream().getText(interval)); */
        }


    }




 /*   @Override public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando entra no atomic, printa o inicio das transações
        printTree(transactionInit());//printa o inicio da transação e depois ele visita o próximo nodo
    }

    @Override public void exitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando sai do atomic printa o final das transações
        printTree(transactionTest());//printa o final da transação
    }

    @Override public void enterMethodInvocation(Java9Parser.MethodInvocationContext ctx) {//verifica se está dentro de um atomic
        if(findAtomicParent(ctx)){//se estiver dentro da subárvore de um atomic
            printTree(addTransacionToMethod(ctx.getText()));//adiciona transação no método
        }
        else{//senão, imprime do jeito que for.
            printTree(ctx.getText()+";");
        }
    } */

    //  MÉTODOS
    private void printTree(String argument){//Método para imprimir as coisas, pode ser modificado depois
        System.out.println(argument);
    }

    private boolean findAtomicParent(ParserRuleContext node) {//verifica se está dentro de um atomic
        node = node.getParent();// a regra atual é uma qualquer além do atomic, então posso começar a subir a àrvore
        while (node != null) {//se o parent node é null, é a raiz da árvore
            if (node.getRuleIndex() == ATOMICINDEX) { // 6 é o index da regra atomic
                return true; //Se entre os nós-pais encontrei um seis, significa que está dentro de um atomic
            }
            node = node.getParent();//continua subindo
        }
        return false;
    }

    private String addTransacionToMethod (String invocation){//adiciona transição na invocação de um método
        int inserir = invocation.indexOf('(')+1; //onde inserir o t, logo depois do primeiro "("
        return "\n" + invocation.substring(0,inserir) + "t," + invocation.substring(inserir) +";";//manipula o trecho do texto e devolve ele
    }

    private String transactionInit(){//Inicio da transação
        return "Trans t = new Trans();\n" +
                "t.start();\n"+
                "while (t.state != COMMITED) {";
    }

    private String transactionTest() {//fim da transação
        return "\nswitch(t.state){" +
                "\ncase RETRY: " +
                "\n\tt.retry();" +
                "\n\t break;" +
                "\ncase ACTIVE: " +
                "\n\tt.commit();" +
                "\n\tbreak;" +
                "\ncase ABORTED: " +
                "\n\tt.rollback();" +
                "\n\tbreak;" +
                "\ndefault:" +
                "\n\tbreak;" +
                "}";
    }
}