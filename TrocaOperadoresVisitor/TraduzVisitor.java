import org.antlr.v4.runtime.ParserRuleContext;
public class TraduzVisitor extends TrocaOperadoresBaseVisitor<Void>{//o visitor tem um tipo associado
	public Void visitNumero(TrocaOperadoresParser.NumeroContext ctx) {
	String s = ctx.getText();
	Integer n = Integer.parseInt(s);
	System.out.printf(Integer.toBinaryString(n)); 
	return visitChildren(ctx); 
	}
	public Void visitMAIS(TrocaOperadoresParser.MAISContext ctx) { 
	System.out.print(" and ");
	return visitChildren(ctx);
	}
	public Void visitMENOS(TrocaOperadoresParser.MENOSContext ctx) { 
	System.out.print(" or ");	
	return visitChildren(ctx); 
	}

}
