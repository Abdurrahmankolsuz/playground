#pragma once

#ifndef CONTROLLER_H
#define CONTROLLER_H

class Controller{
public:
	Controller();
	int setLeftRight();					//saðdan veya soldan arabanýn gelmesini saðlayacak								+
	void levelUp();						//leveli arttýracak																-
	void isDead();						//hayvanýn ölüp ölmediðini kontrol edecek										-
	void gotoxy(int,int);				//terminalde x ve y koordinatlarýna gidecek										+
	void generateVehicle(int, int);		//ekrana vehicle class'ýnda belirtilen tipte araba bastýr						/
	void clearScreen();					//ekran temizlemek için															+


private:
	int left_right;
};






#endif
