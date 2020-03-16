import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.misc.Interval;

import org.antlr.v4.runtime.*;


public class AtomicListener extends Java9BaseListener {
    //Cada regra da gramática tem um índice. Posso descobrir esse índice usando ctx.getRuleIndex(), e printar
    //usando ctx.getText() só o que me interessa
    final int ATOMICINDEX = 6;//número da regra geradora do atomic.
    ///*
    @Override
    public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando entra no atomic, printa o inicio das transações
        //replace do token atomic pelo inicio da transação. O "{" logo em seguida do atomic já funciona como o
        // "{" que deve vir logo após o while no código gerado
        Tradutor.rewriter.replace(ctx.getStart(), transactionInit());//Troca o token inicial (atomic)
        // por todo o código gerado definido em trasactionInit(). Agora a árvore vai ser percorrida.
    }

    @Override
    public void exitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {//quando sai do atomic salva o final das transações
        //as alterações já foram lidadas nos outros métodos, então só troco o token final pelo código
        //gerado relativo ao final das transações.
        Tradutor.rewriter.replace(ctx.getStop(),transactionEnd() );
    }

    @Override
    public void enterMethodInvocation(Java9Parser.MethodInvocationContext ctx) {//verifica se está dentro de um atomic
        if (isInsideAtomic(ctx)) {//se estiver dentro da subárvore de um atomic
            //redução extrema do código que eu vinha implementando até aqui. Pega todo o contexto da variável,
            //adiciona a transação e substitui o resultado disso, que é o método com a adição do parâmetro
            //da transação, no código, deixando só a parte que é importante pro código.
            Tradutor.rewriter.replace(ctx.getStart(), ctx.getStop(), addTransacionToMethod(ctx.getText()));//adiciona transação no método e já altera nos tokens
        }
        //caso contrário, nenhuma alteração é feita
    }


    //*/

    //  MÉTODOS

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
        return  invocation.substring(0, inserir) + "t," + invocation.substring(inserir);//manipula o trecho do texto e devolve ele
    }

    private String transactionInit() {//Inicio da transação
        return "Trans t = new Trans();\n" +
                "t.start();\n" +
                "while (t.state != COMMITED) ";
    }

    private String transactionEnd() {//fim da transação
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