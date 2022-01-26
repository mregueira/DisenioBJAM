//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_CALIPERMANAGER_H
#define JSON_TESTBENCH_CALIPERMANAGER_H

#include "../../Common/Inc/BUFFER_SIZE.h"
#include "../../../TestingResources/Inc/CODE_TESTING.h"

#define RETRY_TIMES 3


#include "stdbool.h"

#include "../../EthernetEvent/Inc/ethernet.h"
#include "../../Messages/Send/Inc/messageLayer.h"


#ifdef TESTING
#include "../../testBenchUtils/Inc/caliper_number.h"
#include "../../testBenchUtils/Inc/digimatic_measure_t.h"
#include "../../testBenchUtils/Inc/message.h"
#include "../../testBenchUtils/Inc/testBenchVars.h"

typedef uint8_t digimatic_frame_t;
digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper);
#else
#include "../../DigimaticProtocol/Inc/digimatic.h"
#include "../../Common/Inc/message.h"
#endif

void caliperManager(caliper_number caliperNumber);

#endif //JSON_TESTBENCH_CALIPERMANAGER_H
