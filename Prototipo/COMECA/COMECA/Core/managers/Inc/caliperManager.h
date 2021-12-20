//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_CALIPERMANAGER_H
#define JSON_TESTBENCH_CALIPERMANAGER_H

#include "../../Inc/BUFFER_SIZE.h"
#include "../../Inc/CODE_TESTING.h"

#define RETRY_TIMES 3


#include "stdbool.h"

#include "../../utils/Inc/messageLayer.h"
#include "../../utils/Inc/ethernet.h"

#ifdef TESTING
#include "../../testBenchUtils/Inc/caliper_number.h"
#include "../../testBenchUtils/Inc/digimatic_measure_t.h"
#include "../../testBenchUtils/Inc/message.h"
#include "../../testBenchUtils/Inc/testBenchVars.h"

typedef uint8_t digimatic_frame_t;
digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper);
#else
#include "../../Inc/digimatic.h"
#include "../../Inc/message.h"
#endif

void caliperManager(caliper_number caliperNumber);

#endif //JSON_TESTBENCH_CALIPERMANAGER_H
