package CaseStudy2;

import java.util.*;
import java.text.*;

/*Creating a class Customer with all the customer's information like Customer Name, Card Number, Phone Number, City, Amount, Date of issue, Expiry date and a static integer to count the total number of Accounts in the bank */

class Customer{
    static int t = 0;
    protected String CustomerName; 
    public int CardNumber;
    protected int Amount;
    protected long PhoneNumber;
    protected int Pin;
    protected String City;
    public String DateOfIssue;
    public String ExpiryDate;

    /*This is a constructor used to initialise the above parameters */

    Customer(String CustomerName,int Pin,int Amount,String City,long PhoneNumber){
        this.CustomerName = CustomerName;
        this.Pin = Pin;
        this.City = City;
        this.Amount = Amount;
        setDateOfIssue();
        t++;
        this.CardNumber = 10000+t;
        this.PhoneNumber = PhoneNumber;
    }

    /*Also Date of issue and Expiry date is set using the below function */

    public void setDateOfIssue(){
        Date IssueDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Calendar c = Calendar.getInstance();
        c.setTime(IssueDate);
        c.add(Calendar.YEAR, 4);
        Date ExpiryDat = c.getTime();

        this.DateOfIssue = formatter.format(IssueDate);
        this.ExpiryDate = formatter.format(ExpiryDat);
    }

    /*Encapsulation - getPin function to get the user's pin */
    protected int getPin(){
        return this.Pin;
    } 
    
    /*CustomerDetails to display the details of customer  */
    protected void CustomerDetails(){
        System.out.println("NAME OF THE CARD HOLDER : " + this.CustomerName);
        System.out.println("THE PHONE NUMBER OF THE CARD HOLDER IS : " + this.PhoneNumber);
        System.out.println("THE CARD NUMBER IS : " + this.CardNumber);
        System.out.println("THE ADDRESS OF THE CARD HOLDER IS : " + this.City);
        System.out.println("THE BALANCE AMOUNT IS : " + this.Amount);
        System.out.println("THE DATE OF ISSUE : " + this.DateOfIssue);
        System.out.println("THE EXPIRY DATE IS : " + this.ExpiryDate);
    }
}

/*Class CustomerArray is created for addition of new accounts and all the methods of withdrawal, deposit, balance, and editing basic information of the account */
class CustomerArray{
    protected ArrayList<Customer> obj = new ArrayList<Customer>();
    

    protected void AddCustomer(String CustomerName,int Pin,int Amount,String City,long PhNum){
        obj.add(new Customer(CustomerName, Pin, Amount, City, PhNum));
    }

    protected Boolean CheckCustomer(int CardNumber, int Pin){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                return true;
        }
        return false;
    }

    protected String getName(int CardNumber, int Pin){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                return obj.get(i).CustomerName;
        }
        return "";
    }

    protected String getCity(int CardNumber, int Pin){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                return obj.get(i).City;
        }
        return "";
    }

    protected void EditName(int CardNumber, int Pin, String ChangedName){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                obj.get(i).CustomerName = ChangedName;
        }
    }

    protected void EditCity(int CardNumber, int Pin, String ChangedCity){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                obj.get(i).City = ChangedCity;
        }
    }

    protected void EditPhoneNumber (int CardNumber, int Pin, long ChangedNumber){
        for(int i = 0 ; i < obj.size() ; i++){
            if(obj.get(i).CardNumber == CardNumber && obj.get(i).Pin == Pin)
                obj.get(i).PhoneNumber = ChangedNumber;
        }
    }

    protected void Deposit(int CardNumber, int Pin,int money){
        for(int i = 0 ; i < obj.size() ; i++){
            Customer c = obj.get(i);
            if(c.CardNumber == CardNumber){
                if(c.Pin == Pin){
                    c.Amount += money;
                    System.out.println("THE MONEY IS DEPOSITED");
                }

                else{
                    System.out.println("The entered Pin is wrong");
                }
            }

            else{
                System.out.println("This Card number does not exist!");
                
            }
        }
    }

    protected int Withdraw(int CardNumber, int Pin,int money){
        int check = 1;
        for(int i = 0 ; i < obj.size() ; i++){
            Customer c = obj.get(i);
            if(c.CardNumber == CardNumber){
                if(c.Pin == Pin){
                    if(money < c.Amount){
                        c.Amount -= money;
                        System.out.println("THE MONEY CAN BE COLLECTED");
                    }
                    else{
                        System.out.println("THE ENETERED AMOUNT EXCEEDS YOUR BALANCE..");
                    }
                }

                else{
                    System.out.println("The entered Pin is wrong");
                }
            }

            else{
                System.out.println("This Card number does not exist!");
                check = 0;
            }
        }

        return check;
    }

    protected void Balance(int CardNumber, int Pin){
        for(int i = 0;i < obj.size();i++){
            Customer c = obj.get(i);
            if(c.CardNumber == CardNumber && c.Pin == Pin){
                System.out.println("THE BALANCE IN YOUR ACCOUNT IS : "+c.Amount);
            }
            else{
                System.out.println("Please recheck your entered credentials!");
            }
        }   
    }

    protected void Details(int CardNumber, int Pin){
        for(int i = 0;i < obj.size();i++){
            Customer c = obj.get(i);
            if(c.CardNumber == CardNumber && c.Pin == Pin){
                c.CustomerDetails();
            }
        }
    }

    protected void CusDetails(String Name, String City){
        for(int i = 0;i < obj.size();i++){
            Customer c = obj.get(i);
            if(c.CustomerName == Name && c.City == City){
                c.CustomerDetails();
            }
        }
    }
}

/*Created a class Denominations which can be put to further use - created dynamically so as to remove or add a currency to the system */
class Denominations{
    int currency;
    Denominations(int currency){
        this.currency = currency;
    }
}

class BankingDenominations{
    protected ArrayList<Denominations> denominations = new ArrayList<Denominations>();

    protected int c[] = {1,2,5,10,20,50,100,200,500,2000};

    BankingDenominations(){
        for(int i = 0;i < c.length;i++){
            denominations.add(new Denominations(c[i]));
        }
    }

    protected void addDenomination(int curr){
        denominations.add(new Denominations(curr));
    }

    protected void deleteDenominations(int curr){
        for(int i = 0;i < denominations.size();i++){
            if(denominations.get(i).currency == curr){
                denominations.remove(i);
            }
        }
    }

    protected void deposit(int amount){
        int ind = denominations.size();
        System.out.println("Place your denominations as follows : ");
        while(amount > 0){
            if(amount/(denominations.get(ind-1).currency) > 0){
                amount -= denominations.get(ind-1).currency;
                System.out.println(denominations.get(ind-1).currency+"  ");
            }
        }
    }
}

public class Main{

    /*Created a user friendly menu which easily guides the user to access the system */
    static void Menu(){
        System.out.println("1. LOGIN");
        System.out.println("2. SIGN UP");
        System.out.println("3. EXIT");
    }

    static void TransactionMenu(){
        System.out.println("1. WITHDRAW");
        System.out.println("2. DEPOSIT");
        System.out.println("3. CHECK BALANCE");
        System.out.println("4. EDIT ACCOUNT INFORMATION");
    }

    static void EditMenu(){
        System.out.println("1. EDIT NAME");
        System.out.println("2. EDIT CITY");
        System.out.println("3. EDIT PHONE NUMBER");
    }

    public static String generateOTP() 
    {  
        int randomPin = (int)(Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp; 
    }
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        CustomerArray Obj = new CustomerArray();
        BankingDenominations obj2 = new BankingDenominations();

        System.out.println("        WELCOME!!\n\n");
        int c = 1;

        /*CODE FOR THE MAIN ATM SYSTEM */
        while(c == 1){
            int select;
            Menu();
            System.out.println("\nPLEASE ENTER A NUMBER ACCORDINGLY : ");
            select = sc.nextInt();
            if(select == 1){
                System.out.println("PLEASE ENTER YOUR CARD NUMBER : ");
                int CardNum = sc.nextInt();
                System.out.println("PLEASE ENTER YOUR PIN : ");
                int Pin = sc.nextInt();

                Boolean check = Obj.CheckCustomer(CardNum, Pin);
                if(check){
                    TransactionMenu();
                    System.out.println("\nPLEASE ENTER A NUMBER ACCORDINGLY : ");
                    int t = sc.nextInt();

                    if(t == 1){
                        System.out.println("PLEASE ENTER THE MONEY TO BE WITHDRAWN : ");
                        int money = sc.nextInt();
                        Obj.Withdraw(CardNum, Pin, money);
                    }

                    else if(t == 2){
                        System.out.println("PLEASE ENTER THE MONEY TO BE DEPOSITED : ");
                        int money = sc.nextInt();
                        Obj.Deposit(CardNum, Pin, money);
                    }

                    else if(t == 3){
                        Obj.Balance(CardNum, Pin);
                    }

                    else if(t == 4){
                        EditMenu();
                        System.out.println("PLEASE ENTER A NUMBER ACCORDINGLY : ");
                        int ch = sc.nextInt();
                        if(ch == 1){
                            sc.nextLine();
                            System.out.println("PLEASE ENTER THE NAME : ");
                            String ChangedName = sc.nextLine();
                            Obj.EditName(CardNum, Pin, ChangedName);
                            Obj.Details(CardNum,Pin);
                        }

                        else if(ch == 2){
                            sc.nextLine();
                            System.out.println("PLEASE ENTER THE CITY : ");
                            String ChangedCity = sc.nextLine();
                            Obj.EditCity(CardNum, Pin, ChangedCity);
                            Obj.Details(CardNum,Pin);
                        }

                        else if(ch == 3){
                            System.out.println("PLEASE ENTER THE PHONE NUMBER : ");
                            long ChangedNum = sc.nextLong();
                            Obj.EditPhoneNumber(CardNum, Pin, ChangedNum);
                            Obj.Details(CardNum,Pin);
                        }
                    }

                    else{
                        System.out.println("Entered number is not in the above category!");
                    }
                }
                
                else{
                    System.out.println("THE ENTERED CREDENTIALS DOES NOT MATCH ANY ACCOUNT!");
                }
            }
            else if(select == 2){
                sc.nextLine();
                System.out.println("PLEASE ENTER YOUR NAME : ");            
                String Name = sc.nextLine();
                System.out.println("PLEASE ENTER YOUR CITY : ");
                String City = sc.nextLine();
                System.out.println("PLEASE ENTER YOUR PHONE NUMBER : ");
                long PhNum = sc.nextLong();  
                sc.nextLine();
                
                String Ot = generateOTP();
                System.out.println("THE OTP IS SENT TO YOUR PHONE WITH NUMBER '"+PhNum+"' OTP : "+ Ot);
                System.out.println("ENTER THE OTP : ");
                String otp = sc.nextLine();

                if(Ot.equals(otp)){
                    System.out.println("PLEASE ENTER A PIN : ");
                    int pin = sc.nextInt();

                    System.out.println("PLEASE RE-ENTER A PIN : ");
                    int checkPin = sc.nextInt();
            
                    if(pin == checkPin){
                        System.out.println("PLEASE DEPOSIT AN AMOUNT : ");
                        int Amount = sc.nextInt();
                    
                    //obj2.deposit(Amount);
                        Obj.AddCustomer(Name, pin, Amount, City, PhNum);
                        System.out.println("\nTHE ACCOUNT IS CREATED!!");

                        Obj.CusDetails(Name, City);
                    }

                    else{
                        System.out.println("The entered pin is not correct!");
                    }
                }
                
                else{
                    System.out.println("THE ENTERED OTP IS WRONG");
                }  
            }

            else if(select == 3){
                System.out.println("EXITING THE SYSTEM...");
                c = 0;
            }

            else{
                System.out.println("Entered number is not in the above category!");
            }
        }
        sc.close();
    }
}