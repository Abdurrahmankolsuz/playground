#include "platform.h"
#include "Controller.h"
#include "Vehicle.h"
using namespace std;

void Controller::gotoxy(int x, int y)
{
	COORD coord;
	coord.X = x;
	coord.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

void Controller::generateVehicle(int height,int width)
{
	cout << '\n';
	// top line and corners
	cout << (char)0xDa;
	for(int i=0; i<width-2; ++i)
	cout << (char)0xC4;
	cout << (char)0xBF;
	cout << '\n';
	// vertical edges
	for(int i=0; i<height-2; ++i)
	{
	cout << (char)0xB3;
	for(int j=0; j<height-2; ++j)
	cout << ' ';
	cout << (char)0xB3;
	cout << '\n';
	}
	// bottom line and corners
	cout << (char)0xC0;
	for(int i=0; i<width-2; ++i)
	cout << (char)0xC4;
	cout << (char)0xD9;
	cout << '\n';
}

void Controller::clearScreen()
{
	system("CLS");
}

void Controller::isDead()
{
	
}

int setLeftandRight()
{
	// 1 soldan giriþ
	// 2 saðdan giriþ
	int a;
	srand(time(NULL));
	a = rand()%3;
	if(a!=0)
	{
		return a;
	}
	else
	{
		return a+1;
	}
}

