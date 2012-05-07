#ifndef OPEN_IMAGE_H
#define OPEN_IMAGE_H

#include <QtGui/QMainWindow>
#include "open_image.h"

class open_image : public QMainWindow, private Ui::open_imageClass
{
 Q_OBJECT

public:
 open_image(QWidget *parent = 0, Qt::WFlags flags = 0);
 ~open_image();

private:
 Ui::open_imageClass ui;

 private slots:
   void doThingA(void);
};

#endif // OPEN_IMAGE_H
