//
// Created by joa-m on 11/1/2021.
//

#include "../Inc/testBenchVars.h"

int adc_measure;
char testBench[1000];
bool isValidCaliperMeasure = true;
digimatic_measure_t measure;

int getAdcMeasure(void){ return adc_measure;}
void setAdcMeasure(int measure){adc_measure = measure;}
char* getTestBench(void){return testBench;}
void setValidCaliperMeasure(bool value){
    isValidCaliperMeasure = value;
}
bool getIsValidCaliperMeasure(void){ return isValidCaliperMeasure; };
void setDigimaticMeasure(float number, bool mmOrInches){
    measure.number = number;
    measure.unit = mmOrInches;
}

digimatic_measure_t getDigimaticMeasure(void){ return measure; };