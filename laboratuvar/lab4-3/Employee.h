// Lab 3: Employee.h
// Employee class definition.
#include <string> // program uses C++ standard string class 
using namespace std;
// Employee class definition
class Employee{
public:
        Employee( string, string, int ); // constructor sets data members
        void setFirstName( string ); // set first name
        string getFirstName(); // return first name
        void setLastName( string ); // set last name
        string getLastName(); // return last name
        void setMonthlySalary( int ); // set weekly salary
        int getMonthlySalary(); // return weekly salary
        /* Declare a constructor that has one parameter for each data member */
        /* Declare a set method for the employee’s first name */
        /* Declare a get method for the employee’s first name */
        /* Declare a set method for the employee’s last name */
        /* Declare a get method for the employee’s last name */
        /* Declare a set method for the employee’s monthly salary */
        /* Declare a get method for the employee’s monthly salary */
private:
        string firstName; // Employee's first name
        string lastName; // Employee's last name
        int monthlySalary; // Employee's salary
        /* Declare a string data member for the employee’s first name */
        /* Declare a string data member for the employee’s last name */
        /* Declare an int data member for the employee’s monthly*/
};