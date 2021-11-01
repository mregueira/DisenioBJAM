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

#else


// SALIDAS DIGITALES
// {PC9, PC7, PD15, PD13}
GPIO_TypeDef* outputGpioPortMapping[] = {GPIOC,GPIOC,GPIOD,GPIOD};
uint16_t outputGpioPinMapping[] = {GPIO_PIN_9,GPIO_PIN_7,GPIO_PIN_15,GPIO_PIN_13};

void turnOn(int outputNum){
    HAL_GPIO_WritePin(outputGpioPortMapping[outputNum], outputGpioPinMapping[outputNum], GPIO_PIN_SET);
}
void turnOff(int outputNum){
    HAL_GPIO_WritePin(outputGpioPortMapping[outputNum], outputGpioPinMapping[outputNum], GPIO_PIN_RESET);
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
