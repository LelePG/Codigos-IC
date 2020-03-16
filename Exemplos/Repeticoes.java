public class Atomic {
    public static void main(String[] args) {
        Account ac1 = 1;
        int count;
        atomic {
            for (count = 0; count < 10; count++) {
                a1.deposit(10);
            }
            while (count > 0) {
                a1.deposit(10);
                count--;
            }
            do {
                a1.withdraw(1);
                count++;
            } while (count != 10)
        }
        System.out.println(count);
    }
}