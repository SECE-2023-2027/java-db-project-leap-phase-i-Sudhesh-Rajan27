package javaa;
import java.util.*;
class bbank
{
	private int balance;
	bbank(int amount)
	{
		this.balance=amount;
	}
	
	public void withdraw(int amount)
	{
		if(amount<=balance)
		{
			this.balance-=amount;
		}
		else
		{
			System.out.println("insufficient balance");
		}
	}
	public void deposit(int amount)
	{
		if(amount>0)
		{
			this.balance+=amount;
		}
		else {
			System.out.println("Enter the valid amount");
		}
	}
	public void checkbalance()
	{
		System.out.println("Balance in your acoount is "+this.balance);
	}
	
	public void transaction(bbank b,int amount)
	
	{
		this.balance-=amount;
		b.balance+=amount;
	}
	
	
}

public class bank {
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int initamount1,initamount2;
		System.out.println("Enter the initial amount to deposit in your bank account 1");
		initamount1=sc.nextInt();
		System.out.println("Enter the initial amount to deposit in your bank account 2");
		initamount2=sc.nextInt();
		bbank acc1=new bbank(initamount1);
		bbank acc2=new bbank(initamount2);
		acc1.deposit(30939329);
		acc1.withdraw(8483);
		acc1.checkbalance();
		
		System.out.println("enter the amount for transaction");
		int amount=sc.nextInt();
		acc1.transaction(acc2, amount);
		System.out.print("balace in the 2 nd account is ");
acc1.checkbalance();
sc.close();
	}

}