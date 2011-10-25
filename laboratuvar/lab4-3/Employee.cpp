// Lab 3: Employee.cpp
// Employee class member-function definitions.
#include <iostream>
using namespace std;

#include "Employee.h" // Employee class definition

// Employee constructor initializes the three data members
Employee::Employee( string first, string last, int salary )
{
        setFirstName( first ); // store first name
        setLastName( last ); // store last name
        setMonthlySalary( salary ); // validate and store monthly salary
} // end Employee constructor

// set first name
void Employee::setFirstName( string name )
{
        firstName = name; // no validation needed
} // end function setFirstName

// return first name
string Employee::getFirstName()
{
        return firstName;
} // end function getFirstName

// set last name
void Employee::setLastName( string name )
{
        lastName = name; // no validation needed
} // end function setLastName
// return last name
string Employee::getLastName()
{
        return lastName;
} // end function getLastName

// set monthly salary; if not positive, set to 0.0
void Employee::setMonthlySalary( int salary )
{
if ( salary > 0 ) // if salary is positive
        monthlySalary = salary; // set monthlySalary to salary

if ( salary <= 0 ) // if salary is not positive
        monthlySalary = 0; // set monthlySalary to 0.0
} // end function setMonthlySalary

// return monthly salary
int Employee::getMonthlySalary()
{
        return monthlySalary;
} // end function getMonthlySalary