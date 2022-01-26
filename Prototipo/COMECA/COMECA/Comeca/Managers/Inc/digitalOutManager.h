//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_DIGITALOUTMANAGER_H
#define JSON_TESTBENCH_DIGITALOUTMANAGER_H


#ifdef TESTING
#include "stdio.h"
#include "../../../testBenchUtils/Inc/testBenchVars.h"
#include "../../../testBenchUtils/Inc/message.h"
#else
#include "stm32f4xx_hal.h"
#include "../../Common/Inc/message.h"
#endif

#include "../../Messages/Receive/Inc/jsonGetter.h"

void digitalOutManager(message_t json);

#endif //JSON_TESTBENCH_DIGITALOUTMANAGER_H
