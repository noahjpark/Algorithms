public class Test {
    public static void main(String[] args){
        String[] stringArray = new String[2];
        BankAccount a = new BankAccount("Henry", "Passwore10293834", 1000);
        a.withdraw(30);
        System.out.println(a.getBalance());
    }
}
