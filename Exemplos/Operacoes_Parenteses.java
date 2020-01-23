public class Atomic{
    public static void main(String[] args){
        Account ac1 = 1;
        Account ac2 = 2;
        Account ac3 = 3;
        atomic{
            ac1.deposit(ac3.getText());
            ac2.withdraw(10);
            ac3.deposit(30);
            ac2.deposit(ac1.getText(ac2.getText(ac3.getText())));//código que não faz sentido
        }
    }
}