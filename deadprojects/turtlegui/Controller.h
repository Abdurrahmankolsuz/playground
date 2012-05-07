#pragma once

#ifndef CONTROLLER_H
#define CONTROLLER_H

class Controller{
public:
	Controller();
	int setLeftRight();					//sa�dan veya soldan araban�n gelmesini sa�layacak								+
	void levelUp();						//leveli artt�racak																-
	void isDead();						//hayvan�n �l�p �lmedi�ini kontrol edecek										-
	void gotoxy(int,int);				//terminalde x ve y koordinatlar�na gidecek										+
	void generateVehicle(int, int);		//ekrana vehicle class'�nda belirtilen tipte araba bast�r						/
	void clearScreen();					//ekran temizlemek i�in															+


private:
	int left_right;
};






#endif
