/* 
 * File:   AccountTest1.cpp
 * Author: mahmutbulut
 *
 * Created on Oct 25, 2011, 9:47:57 AM
 */

#include <stdlib.h>
#include <iostream>
#include "Account.h"

using namespace std;
/*
 * Simple C++ Test Suite
 */

int main(int argc, char** argv) {

std::cout << "AccountTest1 test 1" << std::endl;
Account account1( 50 ); // create Account object
Account account2( 0 ); // create Account object
// display initial balance of each object
cout << "account1 balance: $" << account1.getBalance() << endl;
cout << "account2 balance: $" << account2.getBalance() << endl;

int withdrawalAmount; // stores withdrawal amount read from user 20
cout << "\nEnter withdrawal amount for account1: "; // prompt
cin >> withdrawalAmount; // obtain user input
cout << "\nsubtracting " << withdrawalAmount << " from account1 balance\n\n";
/* write code to withdraw money from account1 */
account1.debit(withdrawalAmount);
// display balances
cout << "account1 balance: $" << account1.getBalance() << endl;
cout << "account2 balance: $" << account2.getBalance() << endl;
cout << "\nEnter withdrawal amount for account2: "; // prompt
cin >> withdrawalAmount; // obtain user input
cout << "\nsubtracting " << withdrawalAmount << " from account2 balance\n\n";
/* write code to withdraw money from account2 */
account2.debit(withdrawalAmount);
// display balances
cout << "account1 balance: $" << account1.getBalance() << endl;
cout << "account2 balance: $" << account2.getBalance() << endl;
    return (EXIT_SUCCESS);
}

