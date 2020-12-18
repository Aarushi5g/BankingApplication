import java.util.InputMismatchException;
import java.util.Scanner;

abstract class BankAccount implements BankInterface{
    private String name;
    private int ac_num;
    private String type;
    public double balance;
    BankAccount(String name,int ac_num, String type, double balance){
        this.name=name;
        this.ac_num=ac_num;
        this.type=type;
        this.balance=balance;
    }
    public void deposit(double amt_dep){
        if (amt_dep<=0){
            throw new IllegalArgumentException("Invalid Deposit Amount!");
        }
        else {
            System.out.println("Current Balance: Rs."+balance);
            balance+=amt_dep;
            System.out.println("Balance after Deposition: Rs."+balance);
        }
    }
    public void withdraw(double amt_with) {
        System.out.println("Current Balance: Rs."+balance);
        if(balance>=amt_with)
        {   
            balance-=amt_with;
            System.out.println("Balance left: Rs."+balance);
        }
        else
        {
            System.out.println("Cannot Withdraw. Not enough money!");
            return;
        }
    }
    void display(){
        System.out.println("\nName of Account Holder: "+name);
        System.out.println("Account Number: "+ac_num);
        System.out.println("Account Type: "+type);
        System.out.println("Balance: Rs."+balance);
    }

}

class Bank extends BankAccount{
     public double interestRate;
        Bank(String name,int ac_num, String type, double balance,double rate) {
        super(name, ac_num, type, balance);
        interestRate = rate;
    }

    public void addInterest() { 
        double interest = balance * interestRate / 100;
        deposit(interest);
        System.out.println("Balance after interest: "+balance);
    }
    public static void main(String args[]) {
        Scanner obj=new Scanner(System.in);
        System.out.println("Enter the Customer Name: ");
        String name= obj.nextLine();
        System.out.println("Enter the Account Number: ");
        int account_no= obj.nextInt();
        obj.nextLine();
        System.out.println("Enter the Account Type: ");
        String type= obj.nextLine();
        System.out.println("Enter the Starting Balance (in Rs.): ");
        double balance= obj.nextDouble();
        System.out.println("Enter the Interest Rate (in %): ");
        double rate= obj.nextDouble();
        Bank c1= new Bank(name, account_no, type, balance,rate);
        int a=0;
        int choice=-1;
        int count = 0;
        double withdraw_charge;
        double dep_amount, with_amount;
        while(a==0){
            System.out.println("\n---------------Menu---------------");
            System.out.println("\n1. Deposit.\n2. Withdraw.\n3. Get Balance after Adding Interest.\n4. Get the Details of your Account.\n5. Exit.");
            System.out.print("\nEnter a choice: ");
            choice=obj.nextInt();
            switch(choice){  
                case 1: System.out.print("Enter amount to deposit (in Rs.): ");
                        try {
                            dep_amount=obj.nextInt();
                            c1.deposit(dep_amount);
                            System.out.println("\nAmount Deposited.");
                        }
                        catch( InputMismatchException exception) {
                            System.out.println("\n******ERROR******:"+"Enter a valid value!\n");
                        }
                        break;
                case 2: System.out.print("Enter amount to withdraw (in Rs.): ");
                        with_amount=obj.nextInt();
                        if(count >0){
                            withdraw_charge = 150;
                            System.out.println("You are withdrawing again .So 150 charge by the bank account.");
                            c1.withdraw(with_amount + withdraw_charge) ;
                            break;                   
                        }
                        else{
                            count +=1;
                            c1.withdraw(with_amount);
                            break;
                        }
                case 3: c1.addInterest();
                        break;
                case 4: c1.display();
                        break;
                case 5: System.exit(0);
            }
        }
    }
}
