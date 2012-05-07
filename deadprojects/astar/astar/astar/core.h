//
//  core.h
//  astar
//
//  Created by Mahmut Bulut on 4/6/12.
//  Copyright (c) 2012 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#ifndef astar_core_h
#define astar_core_h

class Node;
const int n=200; // Yatay uzunluğu haritanın
const int m=100; // Dikey uzunluğu haritanın
static int map[n][m]; // Harita
static int closed_nodes[n][m]; // Denenmiş node'ların haritası
static int open_nodes[n][m]; // Denenmemiş node'ların haritası
static int direction_map[n][m]; // Yönlerin haritası
const int directions=8; 
// gidiş için gerekli olan yön sayısı
/*   1 2 3 
 *   4 ~ 5
 *   6 7 8 
 */

class Node {
private:
    //Şuandaki x ve y pozisyonları
    int currxpos;
    int currypos;
    //Şuana kadar olan tüm yolun uzunluğu (anlık node için)
    int progress;
    // priority equal progress with chebyshev, taxicab or euclidian distances
    int priority;  // smaller: higher priority
    unsigned bitmask = 0xFFF0;
public:
    Node(int xposx, int yposx, int progressx, int priorityx){
        currxpos=xposx;
        currypos=yposx;
        progress=progressx;
        priority=priorityx;
    }
    void updatePriority(int xDef, int yDef){
        priority=progress+predict(xDef, yDef)*10; //A*
    }
    
};

#endif
