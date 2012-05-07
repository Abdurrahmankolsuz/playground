//
//  Vehicle.cpp
//  turtle
//
//  Created by Mahmut Bulut on 12/17/11.
//  Copyright (c) 2011 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#include "platform.h"
#include "Vehicle.h"

#ifdef _WIN32

Vehicle::Vehicle(int wid, int heig, int xcr, int ycr){
    Vehicle::setWidths(wid);
    Vehicle::setHeights(heig);
    Vehicle::setXCoor(xcr);
    Vehicle::setYCoor(ycr);
}

void Vehicle::setXCoor(int x)
{
    xcoor = x;
}

void Vehicle::setYCoor(int y)
{
    ycoor = y;
}

void Vehicle::setWidths(int w)
{
    widths = w;
}

void Vehicle::setHeights(int h)
{
    heights = h;
}

void Vehicle::setTimeLevel(int t)
{
    time_level = t;
}

int Vehicle::getXCoor()
{
    return xcoor;
}

int Vehicle::getYCoor()
{
    return ycoor;
}

int Vehicle::getWidths()
{
    return widths;
}

int Vehicle::getHeights()
{
    return heights;
}

int Vehicle::getTimeLevel()
{
    return time_level;
}
/*
void Vehicle::vehicleGenerator()
{
    
}
*/


#endif