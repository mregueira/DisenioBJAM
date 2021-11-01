//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/digitalOutManager.h"


#ifdef TESTING
void turnOn(int outputNum){
    sprintf(getTestBench(),"ON->%d", outputNum);
}
void turnOff(int outputNum){
    sprintf(getTestBench(),"OFF->%d", outputNum);
}

#endif

void digitalOutManager(message_t json){
    int outputNum = getOutputNum(json);
    char outputState[4];
    getOutputState(json, outputState);

    if(strcmp(outputState, "ON") == 0){
        turnOn(outputNum);
    }
    if(strcmp(outputState, "OFF") == 0){
        turnOff(outputNum);
    }
}