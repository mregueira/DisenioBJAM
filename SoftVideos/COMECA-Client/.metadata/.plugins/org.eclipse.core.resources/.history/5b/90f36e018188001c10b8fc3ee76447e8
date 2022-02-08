//
// Created by joa-m on 11/1/2021.
//
#include "../../../Comeca/Managers/Inc/caliperManager.h"

#ifdef TESTING
digimatic_frame_t* digimaticGetMeasureFramesTest(caliper_number curr_caliper){
    return NULL;
}
digimatic_measure_t digimaticMeasureTest(digimatic_frame_t* digimaticFrame){
    return getDigimaticMeasure();
}
bool validCaliperMeasureTest(digimatic_frame_t* digimaticFrame){
    return getIsValidCaliperMeasure();
}

#endif

void caliperManager(caliper_number caliperNumber){
    int retry;
    bool isValid = false;
    digimatic_frame_t* digimaticFrames;
    for(retry = 0; retry< RETRY_TIMES; retry++){
#ifdef TESTING
        digimaticFrames = digimaticGetMeasureFramesTest(caliperNumber);
        isValid = validCaliperMeasureTest(digimaticFrames);
#else
        digimaticFrames = digimaticGetMeasureFrames(caliperNumber);
        isValid = validCaliperMeasure(digimaticFrames);
#endif
        if(isValid) break;
    }

    if(isValid){
#ifdef TESTING
        digimatic_measure_t measure = digimaticMeasureTest(digimaticFrames);
#else
        digimatic_measure_t measure = digimaticMeasure(digimaticFrames);
#endif
    	sendCaliperMeasure(measure, caliperNumber);
    } else {
    	sendCaliperWarning(caliperNumber);
    }

}
