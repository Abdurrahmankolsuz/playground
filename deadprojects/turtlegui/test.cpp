
#include "platform.h"
#include "Vehicle.h"

using namespace std;

int setLeftandRight()
{
	// 1 soldan giri�
	// 2 sa�dan giri�
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

int main(){
	while(1==1){
	cout << setLeftandRight();
	}

return 0;
}