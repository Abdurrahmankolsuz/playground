// Member-function definitions for class GradeBook.
#include <iostream>
using namespace std;
// include definition of class GradeBook from GradeBook.h
#include "GradeBook.h"
// constructor initializes courseName
// with string supplied as argument
GradeBook::GradeBook( string course, string lavas ){
setCourseName( course ); // initializes courseName
} // end GradeBook constructor
// function to set the course name
void GradeBook::setCourseName( string name ){
courseName = name; // store the course name
} // end function setCourseName
// function to retrieve the course name
string GradeBook::getCourseName(){
return courseName;
} // end function getCourseName
// display a welcome message and the course name
void GradeBook::displayMessage(){
// display a welcome message containing the course name
cout << "Welcome to the grade book for\n" << getCourseName() << "!"<< endl;
} // end function displayMessage
