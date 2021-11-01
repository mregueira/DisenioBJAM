//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_DIGITALOUTMANAGER_H
#define JSON_TESTBENCH_DIGITALOUTMANAGER_H

#include "../../Inc/CODE_TESTING.h"

#ifdef TESTING
#include "stdio.h"
#include "../../testBenchUtils/Inc/testBenchVars.h"
#endif
#include "../../testBenchUtils/Inc/message.h"
#include "../../utils/Inc/jsonGetter.h"

void digitalOutManager(message_t json);

#endif //JSON_TESTBENCH_DIGITALOUTMANAGER_H
