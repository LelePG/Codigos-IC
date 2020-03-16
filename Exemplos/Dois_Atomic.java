public class Atomic {
    public static void main(String[] args) {
        Account ac1 = 1;
        int b1,b2,b3;
        b1=0;
        b2=1;
        b3=10;
        atomic {
            a1.deposit(b3);
        }
        System.out.println("Parte 1");
        for(int c;b3;c++){
            System.out.println(b2 + "Vez:" + b1);
        }
        atomic{
            if(b2==b1){
                a1.deposit(b3);
            }
            else if (b1==b3){
                a1.deposit(5);
            }
        }
    }
}