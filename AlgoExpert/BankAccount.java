public class BankAccount {
    private String name;
    private String password;
    private double balance;

    public BankAccount(String name, String password, double b){
        this.password = password;
        this.name = name;
        balance = b;
    }

    public boolean passwordMatches(String password){
        if(this.password.equals(password)) return true;
        else return false;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        if(amount < balance) balance -= amount;
        else System.out.println("Amount is too large. Cannot withdraw " + amount);
    }

    public void transfer(BankAccount other, String p, double amount) {
        /* if(this.passwordMatches(p) == false){
             print a message saying we couldn't withdraw
           else
              this.withdraw(p, amount)
              Scan for our second password p2
              if(other.passwordMatches(p2) == false)
                 print a message saying transaction failed; money being deposited back into the original account
                 this.deposit(p, amount)
              else
                 print a message saying the password matches and the transaction will go through
                 other.deposit(p, amount)
      }
      */
    }

    public double getBalance(){
        return balance;
    }
}
