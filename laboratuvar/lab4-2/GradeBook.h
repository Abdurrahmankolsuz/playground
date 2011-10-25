/* 
 * File:   GradeBook.h
 * Author: mahmutbulut
 *
 * Created on October 25, 2011, 10:11 AM
 */

#ifndef GRADEBOOK_H
#define	GRADEBOOK_H
 // Lab 2: GradeBook.h
// Definition of GradeBook class that stores an instructor's name.
#include <string> // program uses C++ standard string class
using namespace std;
// GradeBook class definition
class GradeBook{
public:
        // constructor initializes course name and instructor name
        GradeBook( string, string );
        void setCourseName( string ); // function to set the course name
        string getCourseName(); // function to retrieve the course name
        string getInstructorName();/* write code to declare a get function for the instructor’s name */
        void setInstructorName(string);/* write code to declare a set function for the instructor’s name */
        void displayMessage(); // display welcome message and instructor name
private:
        string courseName; // course name for this GradeBook
        string instructorName; // instructor name for this GradeBook
}; // end class GradeBook

#endif	/* GRADEBOOK_H */

