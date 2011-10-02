//
//  main.cpp
//  playground
//
//  Created by Mahmut Bulut on 10/2/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

//#include <iostream>
//
//int main (int argc, const char * argv[])
//{
//
//    // insert code here...
//    std::cout << "Hello, World!\n";
//    return 0;
//}
//

//#include <iostream>
//using namespace std;
//int k=2;
//int f(int a){
//    return k+a;
//}
//int main(){
//    int k = 2;
//    ::k=1;
//    cout << k << endl;
//    {
//        int k = 55;
//        cout << k << endl;
//    }
//    for (int j=0; j<5; j++){
//        int k = j-15;
//        cout << k << endl;
//    }
////    int k = 2; //HATA 1: k değişkeninin aynı scope içinde yeniden deklare edilmesinden doğan hata
//    cout << k << endl;
//    cout<<f(k)<<endl;
////    cout<<j<<endl; //HATA 2: Deklare edilmemiş değişken "j" nin çıktısı istenmiş
//    return 0;
//}

//#include <iostream>
//using namespace std;
//namespace V1 {
//    void f() {cout<<"V1::f"<<endl;}
//    void g() {cout<<"V1::g"<<endl;}
//}
//namespace V2 {
//    void f() {cout<<"V2::f"<<endl;}
//    void g() {cout<<"V2::g"<<endl;}
//    void k() {cout<<"V2::k"<<endl;}
//}
//void h() {
//    using namespace V1;
//    using V2::f;
//    f();
//    g();
//    V1::f();
//}
//using namespace V2;
//int main(){
//    h();
//    f();
//    V1::g();
//    return 0;
//}

//#include <iostream>
//using namespace std;
//int a=2;
//const int b=3;
//const int * ptr1=new int(5);
//int * const ptr2=new int(7);
//int const * const ptr3=new int(10);
//    int main(){
//        int *ptr4=new int(1);
//        int *ptr5=new int[2];
//    for(int i=0;i<2;i++){
//        ptr5[i]=i+(*ptr1);
//    }
//    cout<<"ptr5=["<<*ptr5<<","<<*(ptr5+1)<<"]"<<endl;
//    b=8;
//    *ptr1=a;
//    ptr1=&a;
//    *ptr2=a;
//    ptr2=&a;
//    *ptr3=a;
//    ptr3=&a;
//    *ptr4=a;
//    ptr4=&a;
//    cout<<"ptr1="<<*ptr1<<endl;
//    cout<<"ptr2="<<*ptr2<<endl;
//    cout<<"ptr3="<<*ptr3<<endl;
//    cout<<"ptr4="<<*ptr4<<endl;
//    delete ptr1;
//    delete ptr2;
//    delete ptr3;
//    delete ptr4;
//    delete [] ptr5;
//    return 0;
//}

#include <iostream>
using namespace std;

struct Point{
    int x;
    int y;
};

int addSquare(int,int &);
int add(int,int=1,int=3);
double add(double,double);

Point operator-(const Point &p1,const Point &p2){
    Point p;
    p.x=p1.x-p2.x;
    p.y=p1.y-p2.y;
    return p;
}

int main(){
    int a=5,b=2,c=3;
    Point point1, point2, point3;
    point2.x=2;
    point2.y=2;
    point3.x=3;
    point3.y=7;
//    point1=point1+point2;
    cout<<"point1 + point2="<<"("<<point1.x<<","<<point1.y<<")"<<endl;
    point1=point2-point3;
    cout<<"point1 - point2="<<"("<<point1.x<<","<<point1.y<<")"<<endl;
    cout<<"The sum of squares of "<<a<<" and "<<b<<"is "<<addSquare(a,b)<<endl;
    cout<<"The sum of squares of "<<a<<" and "<<b<<"is "<<addSquare(a,b)<<endl;
    cout<<"The sum of is "<<add(a,c)<<endl;
//    cout<<"The sum of is "<<add()<<endl;
    cout<<"The sum of is "<<add(1.4,5.2)<<endl;
    cout<<"The sum of is "<<add(a)<<endl;
    return 0;
}

int addSquare(int e1,int &e2){
    e1=e1*e1;
    e2=e2*e2;
    return (e1+e2);
}

int add(int c1,int c2,int c3){
    return (c1+c2+c3);
}

double add(double d1,double d2){
    return (d1+d2);
}