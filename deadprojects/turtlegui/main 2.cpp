//
//  main.cpp
//  turtle
//
//  Created by Mahmut Bulut on 12/16/11.
//  Copyright (c) 2011 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//


#include "platform.h"
#include "Vehicle.h"

#ifdef _WIN32
using namespace std;

#pragma region BLOCK_CHARACTER
#ifdef OS_X
const char block = '\u2588';
#endif
#ifdef OS_WIN32
const char block = '#';
#endif
#pragma region BLOCK_CHARACTER



//int main (int argc, const char * argv[])
//{ //			(uzunluk, yükseklik, x noktası başının, y noktası başının)
//	Vehicle car(4, 3, 0, 0);
//	Vehicle tractor(8, 2, 4, 0);
//	_kbhit();
//        cout << block << endl;
//
//cout << '\n';
//// top line and corners
//cout << (char)0xDa;
//for(int i=0; i<10; ++i)
//cout << (char)0xC4;
//cout << (char)0xBF;
//cout << '\n';
//// vertical edges
//for(int i=0; i<10; ++i)
//{
//cout << (char)0xB3;
//for(int j=0; j<10; ++j)
//cout << ' ';
//cout << (char)0xB3;
//cout << '\n';
//}
//// bottom line and corners
//cout << (char)0xC0;
//for(int i=0; i<10; ++i)
//cout << (char)0xC4;
//cout << (char)0xD9;
//cout << '\n';
//
//cout << "press up arrow;" << endl;
//int control = getch();
//int keycode = getch();
//cout << control << endl;
//cout << keycode <<"üst ok" << endl;
//cout << "press down arrow;" << endl;
//control = getch();
//keycode = getch();
//cout << control << endl;
//cout << keycode <<"alt ok" << endl;
//cout << "press sağ arrow;" << endl;
//control = getch();
//keycode = getch();
//cout << control << endl;
//cout << keycode <<"sağ ok" << endl;
//cout << "press sol arrow;" << endl;
//control = getch();
//keycode = getch();
//cout << control << endl;
//cout << keycode <<"sol ok" << endl;
//
//		system("PAUSE");
//        return 0;
//
//
//}

#endif