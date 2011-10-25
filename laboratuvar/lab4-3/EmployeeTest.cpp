// Lab 3: EmployeeTest.cpp
// Create and manipulate two Employee objects.
#include <iostream>
using namespace std;

#include "Employee.h" // include definition of class Employee

// function main begins program execution
int main()
{
// create two Employee objects
Employee employee1( "Bob", "Jones", 2875 );
Employee employee2( "Susan", "Baker", 3150 );

// retrieve and display employee1's monthly salary multiplied by 12
int monthlySalary1 = employee1.getMonthlySalary();
cout << "Employee 1: " << employee1.getFirstName() << " " << employee1.getLastName() << "; Yearly Salary: "<< monthlySalary1 * 12 << endl;

// retrieve and display employee2's monthly salary multiplied by 12
int monthlySalary2 = employee2.getMonthlySalary();
cout << "Employee 2: " << employee2.getFirstName() << " "<< employee2.getLastName() << "; Yearly Salary: "<< monthlySalary2 * 12 << endl;

// give each Employee a 10% raise
cout << "\nIncreasing employee salaries by 10%" << endl;

employee1.setMonthlySalary( monthlySalary1 * 1.1 );
employee2.setMonthlySalary( monthlySalary2 * 1.1 );
// retrieve and display employee1's monthly salary multiplied by 12
monthlySalary1 = employee1.getMonthlySalary();
cout << "Employee 1: " << employee1.getFirstName() << " "<< employee1.getLastName() << "; Yearly Salary: "<< monthlySalary1 * 12 << endl;

monthlySalary2 = employee2.getMonthlySalary();
cout << "Employee 2: " << employee2.getFirstName() << " "<< employee2.getLastName() << "; Yearly Salary: "<< monthlySalary2 * 12 << endl;
} // end main
