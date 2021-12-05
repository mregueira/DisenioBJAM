//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/analogInDigitalOutManager.h"

void analogInDigitalOutManager(message_t json){
    char frameType[100];
    getFrameType(json, frameType);

    if(strcmp(frameType, "READ_ANALOG_IN") == 0){
        analogInManager(json);
    }

    if(strcmp(frameType, "DIGITAL_OUTPUT_SET_STATE") == 0){
        digitalOutManager(json);
    }
}
