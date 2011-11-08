/*
 * money.cpp
 *
 *  Created on: Nov 1, 2011
 *      Author: regularlambda
 */

#include <iostream>
using namespace std;
class Money{
public:
       Money(){}
       Money(long dollars):cents(dollars*100){}
       Money(long dollars, int cent){
    	   if((dollars*cent)<0){
    	          cout<<"illegal values for dolars and cents"<<endl;
    	          exit(1);
    	   }
    	   cents=dollars*100+cent;
       }
       double getValue(){
    	   return (cents*.01);
       }
private:
       long cents;
};

bool equal(Money amount1, Money amount2){
       return (amount1.cents==amount2.cents);
}

Money add(Money amount1, Money amount2){
       Money tmp;
       tmp.cents=amount1.cents+amount2.cents;
       return tmp;
}

class Wallet{
public:
       Wallet(){cash.cents=0;}
       void putMoney(const Money &amount){
             cash.cents+=amount.cents;
}
       double totalCash(){return cash.getValue();}
private:
       Money cash;
};

int main(){
       Money money(5,75);
       cout<<"total money is "<<money.getValue()<<endl;
       Wallet myWallet;
       myWallet.putMoney(add(money,Money(1,50)));
       cout<<"total money int my wallet is "<<myWallet.totalCash()<<endl;
       return 0;
}
