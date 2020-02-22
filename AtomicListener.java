import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.misc.Interval;

import org.antlr.v4.runtime.*;


public class AtomicListener extends Java9BaseListener {
    //Cada regra da gramática tem um índice. Posso descobrir esse índice usando ctx.getRuleIndex(), e printar
    //usando ctx.getText() só o que me interessa
    private final int ATOMICINDEX = 6;//índice da regra do atomic
    private String generetedAtomicCode = "";

    ///*
    @Override
    public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando entra no atomic, printa o inicio das transações
        getAllGeneretedAtomicCode(transactionInit());//salva o inicio da transação e depois ele visita o próximo nodo
    }

    @Override
    public void exitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando sai do atomic salva o final das transações
        getAllGeneretedAtomicCode(transactionTest());//salva o final da transação
        Tradutor.rewriter.insertBefore(ctx.getStart(), this.generetedAtomicCode);//insere todo o texto que foi gerado até aqui
        //antes do primeiro token da varivável de conexto dessa regra, o atomic inicial da regra.
        Tradutor.rewriter.delete(ctx.getStart(), ctx.getStop());//deleta todos os tokens do primeiro ao último, ou seja, todo
        // o contexto que existia dentro da regra do atomic, que é o que foi reescrito.
    }

    @Override
    public void enterMethodInvocation(Java9Parser.MethodInvocationContext ctx) {//verifica se está dentro de um atomic
        if (isInsideAtomic(ctx)) {//se estiver dentro da subárvore de um atomic
            getAllGeneretedAtomicCode(addTransacionToMethod(ctx.getText()));//adiciona transação no método
        } else {//senão, imprime do jeito que for.
            getAllGeneretedAtomicCode(ctx.getText() + ";");
        }
    }
    //*/

    //  MÉTODOS
    private void getAllGeneretedAtomicCode(String argument) {//Método para guardar todo o código que deve ser impresso depois
        this.generetedAtomicCode += argument;//concatena na variável String que criei na classe.
    }

    private boolean isInsideAtomic(ParserRuleContext node) {//verifica se está dentro de um atomic
        node = node.getParent();// a regra atual é uma qualquer além do atomic, então posso começar a subir a àrvore
        while (node != null) {//se o parent node é null, é a raiz da árvore
            if (node.getRuleIndex() == ATOMICINDEX) { // 6 é o index da regra atomic
                return true; //Se entre os nós-pais encontrei um seis, significa que está dentro de um atomic
            }
            node = node.getParent();//continua subindo
        }
        return false;
    }

    private String addTransacionToMethod(String invocation) {//adiciona transição na invocação de um método
        int inserir = invocation.indexOf('(') + 1; //onde inserir o t, logo depois do primeiro "("
        return "\n" + invocation.substring(0, inserir) + "t," + invocation.substring(inserir) + ";";//manipula o trecho do texto e devolve ele
    }

    private String transactionInit() {//Inicio da transação
        return "Trans t = new Trans();\n" +
                "t.start();\n" +
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
                "\n}" +
                "\n}";
    }


}