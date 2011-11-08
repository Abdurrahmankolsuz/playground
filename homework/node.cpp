#include <iostream>
using namespace std;
class Node
{
public:
	Node(int xx = 0, int yy = 0)
		:x(xx), y(yy)
	{
	}
	void setX(int x)
	{
		this->x = x;
	}
	void setY(int y)
	{
		this->y = y;
	}
	int getX()
	{
		return x;
	}
	int getY()
	{
		return y;
	}
private:
	int x;
	int y;
};
class Path
{
public:
	Path(int num = 0)
	{
		numNodes = num;
		kutular = new Node[num];
	}
	Path(const volatile Path& myPath);
	void setNode(int index, int x, int y)
	{
		kutular[index].setX(x);
		kutular[index].setY(y);
	}
	void listNodes()
	{
		cout << "node list" << endl;
		cout << "--------START---------" << endl;
		for (int i = 0; i < numNodes; i++)
			cout << "node " << i << " : " << kutular[i].getX() << ", "<< kutular[i].getY() << endl;
		cout << "--------END---------" << endl << endl;
	}
private:
	Node *kutular;
	int numNodes;
};

int main()
{
	Path myPath(3);
	for (int i = 0; i < 3; i++)
		myPath.setNode(i, i + 1, i + 2);
	myPath.listNodes();
	Path yourPath = myPath;
	yourPath.listNodes();
	myPath.setNode(2, 7, 7);
	myPath.listNodes();
	yourPath.listNodes();
	// Bu yourPath.listNodes(); satõrõ
	// main'in iindeki ilk listNodes'un õktõsõnõ vermesi lazõm
	// sadece Path sõnõfõnõn degisimine izin veriliyor.
	return 0;
}
