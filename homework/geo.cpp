/*
 * geo.cpp
 *
 *  Created on: Nov 1, 2011
 *      Author: regularlambda
 */

#include <iostream>
using namespace std;

class Point{
public:
       Point(int x=0,int y=0){
             {numPoint++;cout<<"Point constructor-"<<numPoint<<endl;}
                myX=x;myY=y;}
       ~Point(){numPoint--;cout<<"Point destructor-"<<numPoint<<endl;}
private:
       int myX;
       int myY;
       static int numPoint;
};
class Line{
public:
       Line(int x1, int y1,int x2, int y2):myEnd1(x1,y1),myEnd2(x2,y2)
             {numLine++;cout<<"Line constructor-"<<numLine<<endl;}
       Line(Point p1,Point p2):myEnd1(p1),myEnd2(p2)
             {numLine++;cout<<"Line constructor-"<<numLine<<endl;}
       Line()
             {numLine++;cout<<"Line constructor-"<<numLine<<endl;}
       ~Line(){numLine--;cout<<"Line destructor-"<<numLine<<endl;}
private:
       Point myEnd1;
       Point myEnd2;
       static int numLine;
};
class Rectangle{
public:
       Rectangle(int x1, int y1,int x2, int y2,int x3, int y3,int x4, int y4)
             :mySide1(x1,y1,x2,y2),mySide2(x3,y3,x4,y4)
           {numRect++;cout<<"Rectangle constructor-"<<numRect<<endl;}
       Rectangle(Line l1,Line l2)
             :mySide1(l1),mySide2(l2)
           {numRect++;cout<<"Rectangle constructor-"<<numRect<<endl;}
       Rectangle()
           {numRect++;cout<<"Rectangle constructor-"<<numRect<<endl;}
       ~Rectangle(){numRect--;cout<<"Rectangle destructor-"<<numRect<<endl;}
private:
       Line mySide1;
       Line mySide2;
       static int numRect;
};
int Rectangle::numRect=0;
int Line::numLine=0;
int Point::numPoint=0;

int main(){
Line line1;
cout<<"---break---"<<endl;
Line line2(2,4,6,8);
cout<<"---break---"<<endl;
{
       Rectangle rect(line1,line2);
}
cout<<"---break---"<<endl;
Rectangle rect;
cout<<"---break---"<<endl;
return 0;
}



