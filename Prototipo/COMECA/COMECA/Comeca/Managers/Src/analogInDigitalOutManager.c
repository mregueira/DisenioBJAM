//
// Created by joa-m on 11/1/2021.
//
#include "../../../Comeca/Managers/Inc/analogInDigitalOutManager.h"

void analogInDigitalOutManager(message_t json){
    char frameType[GLOBAL_MAX_STRING_SIZE];
    getFrameType(json, frameType);

    if(strcmp(frameType, "READ_ANALOG_IN") == 0){
        analogInManager(json);
    }

    if(strcmp(frameType, "setSalidaDigital") == 0){
        digitalOutManager(json);
    }
}
