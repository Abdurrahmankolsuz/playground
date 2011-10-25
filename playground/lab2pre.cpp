//
//  lab2pre.cpp
//  playground
//
//  Created by Mahmut Bulut on 10/14/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include "iomanip"
using namespace std;

void f1();

int mystery2( int x, int y ){
    if(y==0) return x;
    else if(y<0)
        return mystery2( x - 1, y + 1 );
    else
        return mystery2( x + 1, y - 1 );
}

int main()
{
    int x=0;
    cout << "Initially, x = " << x << endl;
    f1();
    cout<<"Attheend,x="<<x<<endl;
    cout<< mystery2(5, 4)<< endl;
    int i;
    int values[ 10 ] = { 4, 1, 1, 3, 4, 9, 9, 2, 1, 7 };
    
    cout << "Element" << setw( 13 ) << "Value" << endl;
    
    for ( i = 0; i < 10; i++ )
        cout<<setw(7)<<i<<setw(13)<<values[i]<<endl;
} // end main

// definition for f1
void f1()
{
int x=3;

cout<<"Duringcalltof1,x="<<x<<endl;
} // end function f1

