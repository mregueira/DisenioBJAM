//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_TESTBENCHVARS_H
#define JSON_TESTBENCH_TESTBENCHVARS_H

#include "stdbool.h"

int getAdcMeasure(void);
void setAdcMeasure(int measure);
char* getTestBench(void);
void setValidCaliperMeasure(bool);
bool getIsValidCaliperMeasure(void);
#endif //JSON_TESTBENCH_TESTBENCHVARS_H
