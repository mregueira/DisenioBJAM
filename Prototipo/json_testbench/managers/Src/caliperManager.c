//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/caliperManager.h"

#ifdef TESTING


// this should come from "digimatic.h"
digimatic_frame_t* digimaticGetMeasureFrames(caliper_number curr_caliper){
    return NULL;
}
// this should come from "digimatic.h"
digimatic_measure_t digimaticMeasure(digimatic_frame_t* digimaticFrame){
    digimatic_measure_t measure;
    measure.number =  1.23452;
    measure.unit = false;
    return measure;
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
        digimaticFrames = digimaticGetMeasureFrames(caliperNumber);
        isValid = validCaliperMeasure(digimaticFrames);
        if(isValid) break;
    }

    char str2send[MAX_STRING_SIZE];
    message_t msg;
    if(isValid){
        msg = sendCaliperMeasure(str2send, digimaticMeasure(digimaticFrames));
    } else {
        msg = sendCaliperWarning(str2send);
    }

    ETHsendMessage(msg);
}