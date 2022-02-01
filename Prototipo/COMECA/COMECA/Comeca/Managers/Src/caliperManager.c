//
// Created by joa-m on 11/1/2021.
//
#include "../../../Comeca/Managers/Inc/caliperManager.h"

#ifdef TESTING


// this should come from "digimatic.h"
digimatic_frame_t* digimaticGetMeasureFrames(caliper_number curr_caliper){
    return NULL;
}
// this should come from "digimatic.h"
digimatic_measure_t digimaticMeasure(digimatic_frame_t* digimaticFrame){
    return getDigimaticMeasure();
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

    if(isValid){
    	digimatic_measure_t measure = digimaticMeasure(digimaticFrames);
    	sendCaliperMeasure(measure, caliperNumber);
    } else {
    	sendCaliperWarning(caliperNumber);
    }

}
