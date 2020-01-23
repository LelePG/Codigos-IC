import org.antlr.v4.runtime.ParserRuleContext;
public class AtomicVisitor extends Java9BaseVisitor<Void>{
    public Void visitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        System.out.println(ctx.getText());
        return visitChildren(ctx);
    }

    @Override public Void visitTypeDeclaration(Java9Parser.TypeDeclarationContext ctx) {
        System.out.println("AAAAAA");
        return visitChildren(ctx); }

/** public Void visitAtomicStatement(Java9Parser.AtomicStatementContext ctx) {
        System.out.println("FOOOOO");

        String testT = "/nwhile(t.state != COMMITED){";
                testT += ctx.getText();
        testT += testTransaction();
        testT+="}";
        return visitChildren(ctx);
    }

    private String testTransaction() {
        return "switch(t.state){" +
                "case RETRY:" +
                "\tt.retry();" +
                "\tbreak;"+
        "case ACTIVE:" +
                "\tt.commit();" +
                "\tbreak;"+
        "case ABORTED:" +
                "\tt.rollback();" +
                "\tbreak;"+
        "default:"+
        "\tbreak;";
    }*/
}
