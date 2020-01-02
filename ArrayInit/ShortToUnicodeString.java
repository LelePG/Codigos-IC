public class ShortToUnicodeString extends ArrayInitBaseListener { //Essa é a file que realmente faz a tradução, recebendo um valor de entrada e imprimindo o correspondente traduzido.
/** Translate { to " */
@Override
public void enterInit(ArrayInitParser.InitContext ctx) {
System.out.print('"');
}
/** Translate } to " */
@Override
public void exitInit(ArrayInitParser.InitContext ctx) {
System.out.print('"');
}
/** Translate integers to 4-digit hexadecimal strings prefixed with \\u */
@Override
public void enterValue(ArrayInitParser.ValueContext ctx) {
// Assumes no nested array initializers
int value = Integer.valueOf(ctx.INT().getText());
System.out.printf("\\u%04x", value);
}
}
