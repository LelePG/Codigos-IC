import org.antlr.v4.runtime.ParserRuleContext;
public class AtomicListener extends Java9BaseListener{

    @Override public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        TransactionCode(ctx.getText());
    }
    //Métodos de manipulação do atomic
    private void TransactionCode(String context){
        System.out.println(TransactionInit());//inicia o código
        System.out.println(TransactionTest(context.substring(7,context.length()-1)));//7 é o final de "atomic{", e o -1 exclui o fecha chaves do final
    }
    private String TransactionInit(){//Cria a transação
        return "Trans t = new Trans();\n" +
                "t.start();";
    }

    private String TransactionAdd(String original){//adiciona a transação nas operações
        int inserir,fim;//onde inserir o caracter, e o fim da substring
        String retorno = "";//armazena o texto formatado
        while(original.contains("(")){// enquanto houver um abre parênteses na substring testada
            fim = original.indexOf(';') +1;//fim da linha/substring pra formatação
            inserir = original.indexOf('(')+1; //onde inserir o t, logo depois do primeiro (
            retorno += "\n" + original.substring(0,inserir) + "t," + original.substring(inserir,fim) ; // divide a linha, e nela insere o t. Depois disso, cria nova linha
            original = original.substring(fim);//depois de inserir o t, aquela linha (até ';') é descartada e a string onde tenho o texto é atualizada
        }
        return retorno;
    }
    private String TransactionTest(String context){//testes da transação
        return "while (t.state != COMMITED) {" +//texto de teste das transações
                TransactionAdd(context) +//material da transação propriamente dito
                "\nswitch(t.state){" +
                "\ncase RETRY: " +
                "\n\tt.retry();"+
                "\n\t break;" +
                "\ncase ACTIVE: " +
                "\n\tt.commit();"+
                "\n\tbreak;" +
                "\ncase ABORTED: " +
                "\n\tt.rollback();"+
                "\n\tbreak;" +
                "\ndefault:"+
                "\n\tbreak;"+
                "}";

    }
}