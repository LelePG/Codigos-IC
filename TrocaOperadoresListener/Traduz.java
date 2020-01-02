import org.antlr.v4.runtime.ParserRuleContext;
public class Traduz extends TrocaOperadoresBaseListener {
	
	@Override public void enterMAIS(TrocaOperadoresParser.MAISContext ctx) { 
	System.out.print(" and ");
	//System.out.println("CTX MAIS:"+ ctx.getText());//ctx
	}
	@Override public void enterMENOS(TrocaOperadoresParser.MENOSContext ctx) {
	System.out.print(" or ");	
	//System.out.println("CTX MENOS:"+ ctx.getText());//ctx
	}
	@Override public void enterNumero(TrocaOperadoresParser.NumeroContext ctx) {
	String s = ctx.getText();
	Integer n = Integer.parseInt(s);
	System.out.println(Integer.toBinaryString(n));
	//System.out.println("CTX NUM:"+ ctx.getText());//ctx	
	}	

}
