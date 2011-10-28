#include <iostream>
#include "Account.h"

using namespace std;
int main(){
Account account1( 799 );
cout << "account1 balance: $" << account1.getBalance() << endl;
cout << "adding -$114 to account1 balance" << endl;
account1.credit( -114 );
cout << "account1 balance: $" << account1.getBalance() << endl;
} // end main
