//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_TESTBENCHVARS_H
#define JSON_TESTBENCH_TESTBENCHVARS_H

#include "../../Inc/CODE_TESTING.h"

#ifdef TESTING

#include "stdbool.h"

int getAdcMeasure(void);
void setAdcMeasure(int measure);
char* getTestBench(void);
void setValidCaliperMeasure(bool);
bool getIsValidCaliperMeasure(void);

#endif

#endif //JSON_TESTBENCH_TESTBENCHVARS_H
