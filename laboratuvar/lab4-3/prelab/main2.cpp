#include <iostream>
#include "GradeBook.h"

int main(){

	GradeBook gradeBook( "Introduction to C++", "Naber");

	cout << "Please enter the course name:" << endl;
	char inputName[500];
	cin.getline( inputName, 500);
	gradeBook.setCourseName(inputName);
	//gradeBook.setCourseName("Advanced C++" );
	cout << "The grade book's course name is: " <<gradeBook.courseName<< endl;
	return 0;
}
