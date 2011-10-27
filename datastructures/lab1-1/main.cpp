#include <QtCore/QCoreApplication>
#include <iostream>
using namespace std;

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);
    const int MAX = 50;
    int eleman;
    cout << "Dizideki elemanların sayısını giriniz: ";
    cin >> eleman;
    if(eleman <= MAX){
        int dizi[eleman];
        cout << "Dizideki elemanları giriniz "<< endl;
        for(int i=0; i<=eleman; i++){
            cin >> dizi[i];
            cout << "alındı" << endl;
        }
        int temp=0;
        for (int i = 1; i < eleman; i++) {
             for (int j = 0; j < eleman - i; j++)
            {
                 if(dizi[j] > dizi[j+1])
                {
                     temp = dizi[j+1];
                     dizi[j+1] = dizi[j];
                     dizi[j] = temp;
                 }
             }
        }
        for(int a=0; a<=eleman; a++){
            cout << dizi[a]<< " " << dizi[a+1];
        }
    }
    else{
        cout << "50'den fazla eleman girdiniz";
    }
    return a.exec();
}
