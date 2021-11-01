//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_CALIPERMANAGER_H
#define JSON_TESTBENCH_CALIPERMANAGER_H

#define MAX_STRING_SIZE 50
#define TESTING

#define RETRY_TIMES 3

#include "../../caliper_number.h"
#include "stdbool.h"
#include "../../message.h"
#include "../../messageLayer.h"
#include "../../ethernet.h"

#ifdef TESTING
#include "../../testBenchVars.h"
typedef uint8_t digimatic_frame_t;
digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper);
#else
#include "digimatic.h"
#endif

#endif //JSON_TESTBENCH_CALIPERMANAGER_H
