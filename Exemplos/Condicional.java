public class Atomic {
    public static void main(String[] args) {
        Account ac1 = 1;
        int count=0;
        atomic {
            if(count == 1){
                a1.deposit(1);
            }
            else if (count==2){
                a1.deposit(2);
            }
            else if(count==3){
                a1.deposit(3);
            }
            else{
                a1.deposit(10000);
            }
        }
        System.out.println(count);
    }
}