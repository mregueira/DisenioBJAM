//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_TESTBENCHVARS_H
#define JSON_TESTBENCH_TESTBENCHVARS_H


#ifdef TESTING
#include <stdint.h>
#include "stdbool.h"
#include "../../Comeca/DigimaticProtocol/Inc/digimatic.h"

int getAdcMeasure(void);
void setAdcMeasure(int measure);
char* getTestBench(void);
void setValidCaliperMeasure(bool);
bool getIsValidCaliperMeasure(void);
void setDigimaticMeasure(float number, bool mmOrInches);
digimatic_measure_t getDigimaticMeasure(void);

#endif

#endif //JSON_TESTBENCH_TESTBENCHVARS_H
