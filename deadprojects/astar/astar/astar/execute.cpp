//
//  core.cpp
//  astar
//
//  Created by Mahmut Bulut on 4/6/12.
//  Copyright (c) 2012 Eskişehir Osmangazi Üniversitesi. All rights reserved.
//

#include <iostream>
#include <stdio.h>
#include <string.h> /* For strcmp() */
#include <stdlib.h> /* For EXIT_FAILURE, EXIT_SUCCESS */
#include <vector> /* For STL */
#include "mat.h"
using namespace std;

int main(int argc, char const *argv[]){
    MATFile *pmat;
    mxArray *pa1, *pa2, *pa3;
    double *casted;
    const char *file = argv[0];
    const char *varname = argv[1];
    
    printf("Opening file %s...\n\n", file);
    pmat = matOpen(file, "r");
    if (pmat == NULL) {
        printf("Error reading file %s\n", file);
        printf("(Do you have reading permission in this directory?)\n");
        return(EXIT_FAILURE);
    }
    
    pa1 = matGetVariable(pmat, varname);
    if (pa1 == NULL) {
        printf("Error reading existing matrix %s\n", varname);
        return(EXIT_FAILURE);
    }
    
    for (<#initialization#>; <#condition#>; <#increment#>) {
        for (<#initialization#>; <#condition#>; <#increment#>) {
            <#statements#>
        }
    }
}
