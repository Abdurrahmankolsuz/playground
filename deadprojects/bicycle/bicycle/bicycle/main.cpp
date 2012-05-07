//
//  main.cpp
//  bicycle
//
//  Created by Mahmut Bulut on 3/20/12.
//  Copyright (c) 2012 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#include <iostream>
#include <cstdlib>
#include <fstream>
using namespace std;

int main (int argc, const char * argv[])
{
    double ratio;
    int revol=0, line=1;
    ifstream myReadFile;
    ofstream myWriteFile;
    cout << "Give revolution(rpm) as integers: ";
    cin >> revol;
    myWriteFile.open("dataout.txt");
    myReadFile.open("datain.txt");
    if (myReadFile.is_open()) {
        while (!myReadFile.eof()) {
            while(myReadFile >> ratio)
            {
                cout << "Bicycle speed for given revolution for "<< line << ". gear is "
                << 0.028*revol*(1/ratio) << endl;
                line++;
                myWriteFile << 0.028*revol*(1/ratio) << "\n";
            }
        }
    }
    myWriteFile.close();
    
    return 0;
}

