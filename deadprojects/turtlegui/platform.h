//
//  platform.h
//  turtle
//
//  Created by Mahmut Bulut on 12/17/11.
//  Copyright (c) 2011 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#ifndef PLATFORM_H
#define PLATFORM_H

//Support for Windows Platforms
#if defined(_WIN32) || defined(WIN32) || defined(__CYGWIN__) || defined(__MINGW32__) || defined(__BORLANDC__)
#define OS_WIN32
#include <iostream>
#include <cstdlib>
#include <io.h>
#include <Windows.h>
#include <conio.h>
#include <ctime>
#endif

//Support for 64-bit Windows platforms
#if defined(_WIN64)
#define OS_WIN64
#include <iostream>
#include <cstdlib>
#include <io.h>
#include <Windows.h>
#include <conio.h>
#include <ctime>
#endif

// Support for *NIX systems
#if defined(__unix) || defined(__posix) || defined(__linux)
#define OS_NIX
#include <iostream>
#include <cstdlib>
#include <termios.h>
#include <unistd.h>
#include <ctime>
#endif

// Support for APPLE systems
// Apple systems take unicode(utf-8) code table as a base.
// So we can embed
//
// █
// FULL BLOCK
// Unicode: U+2588, UTF-8: E2 96 88
//
// in source file.
#if defined(__APPLE__)
#include "TargetConditionals.h"
#ifdef TARGET_OS_MAC
#define OS_X
#include <iostream>
#include <cstdlib>
#include <termios.h>
#include <unistd.h>
#include <ctime>
#endif


#endif
#endif