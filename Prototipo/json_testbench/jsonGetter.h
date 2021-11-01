//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_JSONGETTER_H
#define JSON_TESTBENCH_JSONGETTER_H

#include "message.h"
#include "json.h"

const char * getFrameType(message_t);
int getInputNumber(message_t);
int getOutputNum(message_t);
const char * getOutputState(message_t);

#endif //JSON_TESTBENCH_JSONGETTER_H
