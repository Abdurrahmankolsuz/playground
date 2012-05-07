//
//  Vehicle.h
//  turtle
//
//  Created by Mahmut Bulut on 12/17/11.
//  Copyright (c) 2011 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#pragma once

#ifndef VEHICLE_H
#define VEHICLE_H

class Vehicle {
public:
    Vehicle();
    Vehicle(int wid,int heig,int xcr,int ycr);
    Vehicle vehicleGenerator();
    void setXCoor(int x);
    void setYCoor(int y);
    void setWidths(int w);
    void setHeights(int h);
    void setTimeLevel(int t);
    int getXCoor();
    int getYCoor();
    int getWidths();
    int getHeights();
    int getTimeLevel();

private:
    int xcoor, ycoor;
    int widths, heights;
    int time_level;
};

#endif