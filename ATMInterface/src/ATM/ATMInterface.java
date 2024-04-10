package ATM;
import java.util.Scanner;
public class ATMInterface {
	static Scanner scanner = new Scanner(System.in);
	static Bank b;
	static AccountHolder currentAccountHolder;
	
	public static void main(String[] args) {
        bankAccounts();
        registerOrLogin();
    }
	
	public static void registerOrLogin() {
		System.out.println();
		System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                register();
                login();
                features();
                break;
            case 2:
                login();
                features();
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                break;
        }
	}
	
	public static void bankAccounts() {
		b = new Bank();
		b.addAccountHolder(new AccountHolder("user1", "1234"));
        b.addAccountHolder(new AccountHolder("user2", "5678"));
	}
	
	public static void register() {
        System.out.println("Register for a new account:");
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.next();
        b.addAccountHolder(new AccountHolder(userId, userPin));
        System.out.println("Registration successful!");
    }
	
	public static void login() {
		System.out.println();
		System.out.println("Login to your account");
		System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.next();

        currentAccountHolder = b.validateAccountHolder(userId, userPin);

        if (currentAccountHolder == null) {
            System.out.println("Invalid credentials. Please try again.");
            login();
        } else {
            System.out.println("Login successful! Welcome, " + currentAccountHolder.getUserId() + "!");
        }
	}
	
	public static void features() {
		System.out.println();
		System.out.println("What do you want to do?");
		System.out.println("1. Show Transactions History");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
        boolean flag = true;
        int choice = scanner.nextInt();
        while(flag) {
	        switch(choice) {
	        case 1:
	        	displayTransactionHistory();
	        	features();
	        	break;
	        case 2:
	        	withDraw();
	        	features();
	        	break;
	        case 3:
	        	depositMoney();
	        	features();
	        	break;
	        case 4:
	        	transferFunds();
	        	features();
	        	break;
	        case 5:	
	        	System.out.println("Thank you for using, You account is signed out");
	        	flag = false;
	        	registerOrLogin();
	        }
        }	
	}
	
	private static void displayTransactionHistory() {
        System.out.println("Transaction History:");
        if(currentAccountHolder.getTransactionHistory().size() != 0) {
        for (Transaction transaction : currentAccountHolder.getTransactionHistory()) {
            System.out.println(transaction);
        }
        }
        else {
        	System.out.println("No previous transactions.");
        }
    }
	
	public static void withDraw() {
		System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        
		if(currentAccountHolder.withdraw(amount)) {
			System.out.println("Withdrawal successful!");
		}
		else {
			System.out.println("Insufficient balance or invalid amount. Withdrawal failed.");
		}
	}

	public static void depositMoney() {
		System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        currentAccountHolder.deposit(amount);
		System.out.println("Deposit successful!");
	}
	
	public static void transferFunds() {
		System.out.print("Enter recipient's User ID: ");
        String recipientUserId = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
       
        if (b.transferFunds(currentAccountHolder.getUserId(), recipientUserId, amount)) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed. Check recipient's User ID or insufficient balance.");
        }
	}
	
    
	
}
