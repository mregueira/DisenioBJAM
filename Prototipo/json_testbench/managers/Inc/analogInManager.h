//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_ANALOGINMANAGER_H
#define JSON_TESTBENCH_ANALOGINMANAGER_H

#define TESTING

#ifdef TESTING
#include "../../testBenchVars.h"
#endif

#include "stdbool.h"
#include "../../message.h"
#include "../../jsonGetter.h"
#include "../../messageLayer.h"
#include "../../ethernet.h"

void analogInManager(message_t json);

#endif //JSON_TESTBENCH_ANALOGINMANAGER_H
