//
//  lab2pre2.cpp
//  playground
//
//  Created by Mahmut Bulut on 10/14/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include "iomanip"

using namespace std;
int main()
{
    const int arraySize = 10;
    int a[arraySize]={2,62,4,33,10,12,89,68,45,7};
    int i;
    int insert;
    int data[arraySize];
    
    cout << "Data items in original order\n";
    for(i=0;i<arraySize;i++)
    cout << setw( 4 ) << a[ i ];
    
    for ( int next = 1; next < arraySize; next++ )
    {
        insert = data[ next ];
        
        int moveItem = next;
        while((moveItem>0)&&(data[moveItem-1]<insert))
            {
                data[ moveItem ] = data[ moveItem - 1 ];
                moveItem--;
                }
        
        data[ moveItem ] = insert;
        }
    
    cout << "\nData items in new order\n";
    for(i=0;i<arraySize;i++)
    cout << setw( 4 ) << a[ i ];
    
    cout << endl;
//////////////////

int array[3][4]={{1,2,3,4},{2,3,4,5},{3,4,5,6}};
    cout << "array contains: " << endl;
for(int i=0;i<3;i++) {
    for ( int j = 0; j < 4; j++ ) {
        cout << array[ i ][ j ] << " ";
        }
    cout << endl;
}
    
    
} // end main