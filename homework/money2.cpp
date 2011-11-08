#include <iostream>
using namespace std;

// b) Correct the errors just modifying Money class.
// You are not allowed to change the main function.

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
       long getCents() const{return cents;}
       double getValue(){return (cents*0.01);}
       // Yaptõm yapraaaaaammmm
       Money add(const Money &money){
    	   return this->cents+=money.getCents();
       }
       Money addCents(long cent){
    	   //Retšrn diz
    	   return this->cents+=cent;
       }
       private:
    	   long cents;
};

int main(){
	Money money(5,75);
       cout<<"total money is "<<money.getValue()<<endl;
       cout<<"total money is "<<money.addCents(75).add(Money(2,10)).getValue()<<endl;
       return 0;
}
