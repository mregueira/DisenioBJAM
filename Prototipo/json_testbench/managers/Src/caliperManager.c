//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/caliperManager.h"

#ifdef TESTING


// this should come from "digimatic.h"
digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper){
    return NULL;
}
// this should come from "digimatic.h"
float digimatic2Float(digimatic_frame_t* digimaticFrame){
    return 1.25234;
}
// this should come from "digimatic.h"
bool validCaliperMeasure(digimatic_frame_t* digimaticFrame){
    return getIsValidCaliperMeasure();
}

#endif

void caliperManager(caliper_number caliperNumber){
    int retry;
    bool isValid = false;
    digimatic_frame_t* digimaticFrames;
    for(retry = 0; retry< RETRY_TIMES; retry++){
        digimaticFrames = digimaticGetMeasure(caliperNumber);
        isValid = validCaliperMeasure(digimaticFrames);
        if(isValid) break;
    }

    char str2send[MAX_STRING_SIZE];
    message_t msg;
    if(isValid){
        msg = sendCaliperMeasure(str2send, digimatic2Float(digimaticFrames));
    } else {
        msg = sendCaliperWarning(str2send);
    }

    ETHsendMessage(msg);
}