//
//  lab2pre3.cpp
//  playground
//
//  Created by Mahmut Bulut on 10/14/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//
#include <iostream>
using namespace std;

void f4(char c);

int main()
{
    /////////////
    int array[ 10 ] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    
    for ( int i = 0; i < 10; i++ )
        cout << array[ i ]<<endl;
    /////////////
    cout << "----------------------";
    /////////////
    int array1[ 3 ][ 3 ] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    for ( int o = 0; o < 3; o++)
        for(int k=0; k<3 ;k++)
            cout << array1[ o ][ k ] << endl;
    /////////////
    char myChar;
    cout << "Enter a character: ";
    cin >> myChar;
    
    f4(myChar);
} // end main

void f4(char c){
        cout << "You just entered the character: " << c << endl;
} // end function f4