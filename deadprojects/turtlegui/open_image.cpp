
#include "open_image.h"

open_image::open_image(QWidget *parent, Qt::WFlags flags)
 : QMainWindow(parent, flags)
{
 setupUi(this);
 QObject::connect(button, SIGNAL(triggered()), this, SLOT(doThingA()));
}

open_image::~open_image()
{

}

void open_image::doThingA(void)
{

    label->setPixmap(QPixmap(":/Users/mahmutbulut0/turtlegui/turtle-1.gif", 0, Qt::AutoColor));

}
