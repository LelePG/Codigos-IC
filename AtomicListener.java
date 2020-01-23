import org.antlr.v4.runtime.ParserRuleContext;
public class AtomicListener extends Java9BaseListener{

    @Override public void exitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        System.out.println("SAI" + ctx.getText());
    }
    @Override public void enterAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        TransactionCode(ctx.getText());
    }

    private Void TransactionCode(String context){
        System.out.println(TransactionInit());
        System.out.println(TransactionTest(context.substring(7,context.length()-1)));
        return null;
    }
    private String TransactionInit(){
        return "Trans t = new Trans();\n" +
                "t.start();";
    }
    private String TransactionAdd(String original){
        return original.substring(0,original.indexOf('(')+1) + "t," + original.substring(original.indexOf('(')+1);
    }
    private String TransactionTest(String context){
        return "while (t.state != COMMITED) {\n" +
                TransactionAdd(context) +
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