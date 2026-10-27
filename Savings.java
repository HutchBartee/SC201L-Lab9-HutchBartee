public class Savings extends BankAccount {
        public Savings(int accountNumber, String accountType, String firstName, String lastName,
                       String transactionDate, int transactionAmount,
                       double accountBalance, double interestEarned){
                        super (accountNumber, accountType, firstName, lastName,
                               transactionDate, transactionAmount,
                               accountBalance, interestEarned);
                       }
    
        //Create Override Functions for processInterestEarned                        
        @Override
        public void processInterestEarned(){
                double intAmount = 0.0;
                if((this.accountBalance >= 100.00) && (this.accountBalance <= 999.99)){
                intAmount = this.accountBalance * .05;
                }
                else if(this.accountBalance >= 1000.00){
                intAmount = this.accountBalance * .75;
                }

                this.interestEarned += intAmount;
                this.accountBalance += intAmount;
        }
}
