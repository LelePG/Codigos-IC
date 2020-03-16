public class Atomic {
    public static void main(String[] args) {
        Account ac1 = 1;
        int count=0;
        atomic {
            switch (count){
                case 1:
                    a1.deposit(1);
                    break;
                case 2:
                    a1.deposit(2);
                    break;
                case 3:
                    a1.deposit(3);
                    break;
                default:
                a1.deposit(10000);
            }
        }
        System.out.println(count);
    }
}