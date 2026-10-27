public class Certificate extends BankAccount{
    private boolean withdrawalTaken = false;

    //Overloaded Contructor that uses Super
    public Certificate(int accountNumber, String accountType, String firstName, String lastName,
                       String transactionDate, int transactionAmount,
                       double accountBalance, double interestEarned){
                        super (accountNumber, accountType, firstName, lastName,
                               transactionDate, transactionAmount,
                               accountBalance, interestEarned);
                       }
    //Create Override Functions for processInterestEarned & processWithdrawal
    @Override
    public void processWithdrawal(int amt){
        accountBalance -= amt;
        withdrawalTaken = true;
    }

    public void processInterestEarned(){
        double intAmount = 0.0;
        if(withdrawalTaken == false){
            intAmount = this.accountBalance * .1 / 73;
        }
        else{
            intAmount = this.accountBalance * .025 / 73;
            withdrawalTaken = false;
        }

        this.interestEarned += intAmount;
        this.accountBalance += intAmount;
    }
}
    
