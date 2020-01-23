import org.antlr.v4.runtime.ParserRuleContext;
public class AtomicListener extends Java9BaseListener{

    @Override public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        TransactionCode(ctx.getText());
    }

    private Void TransactionCode(String context){
        System.out.println(TransactionInit());//inicia o código
        System.out.println(TransactionTest(context.substring(7,context.length()-1)));//7 é o final de "atomic{", e o -1 exclui o fecha chaves do final
        return null;
    }
    private String TransactionInit(){
        return "Trans t = new Trans();\n" +
                "t.start();";
    }
    private String TransactionAdd(String original){
        int inserir,fim;//onde inserir o caracter, e o fim da substring
        String retorno = "";
                while(original.contains("(")){// enquanto houver um abre parênteses na substring testada
                    fim = original.indexOf(';') +1;//fim da linha
                    inserir = original.indexOf('(')+1; //onde inserir o t
                    retorno += "\n" + original.substring(0,inserir) + "t," + original.substring(inserir,fim) ; // divide a linha, e nela insere o t. Depois disso, cria nova linha
                    original = original.substring(fim);//depois de inserir o t, tudo pula pra nova linha, depois do ';' que marca o final
                }

        return retorno;
    }
    private String TransactionTest(String context){
        return "while (t.state != COMMITED) {" +//texto de teste das transações
                TransactionAdd(context) +//material da transação propriamente dito
                "\nswitch(transacao.state){" +
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