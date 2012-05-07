#ifndef WINDOW_H
#define WINDOW_H

#include <QMainWindow>
class QAction;
class QLabel;
class Turtle;
class Vehicle;
class Controller;

class Window
{
        Q_OBJECT
public:
        Window(QWidget *parent = 0, Qt::WindowFlags wf = 0);
public:
    Window();
};

#endif // WINDOW_H
