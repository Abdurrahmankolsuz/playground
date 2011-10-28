// Definition of GradeBook class that stores the course name.
#include <string> // program uses C++ standard string class
using namespace std;
// GradeBook class definition
class GradeBook{
public:
// constructor initializes course name and instructor name
GradeBook( string, string );
void setCourseName( string ); // function to set the course name
string getCourseName(); // function to retrieve the course name
void displayMessage(); // display welcome message and course name
private:
string courseName; // course name for this GradeBook
}; // end class GradeBook
