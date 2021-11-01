//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_DIGITALOUTMANAGER_H
#define JSON_TESTBENCH_DIGITALOUTMANAGER_H

#define TESTING

#ifdef TESTING
#include "stdio.h"
#include "../../testBenchVars.h"
#endif
#include "../../message.h"
#include "../../jsonGetter.h"

void digitalOutManager(message_t json);

#endif //JSON_TESTBENCH_DIGITALOUTMANAGER_H
