//
// Created by joa-m on 11/1/2021.
//
#include "testBenchVars.h"

int adc_measure;
char testBench[1000];
bool isValidCaliperMeasure = true;

int getAdcMeasure(void){ return adc_measure;}
void setAdcMeasure(int measure){adc_measure = measure;}
char* getTestBench(void){return testBench;}
void setValidCaliperMeasure(bool value){
    isValidCaliperMeasure = value;
}
bool getIsValidCaliperMeasure(void){ return isValidCaliperMeasure; };