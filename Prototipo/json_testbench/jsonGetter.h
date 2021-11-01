//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_JSONGETTER_H
#define JSON_TESTBENCH_JSONGETTER_H

#include "message.h"
#include "json.h"

void getFrameType(message_t json, char* frameTypePtr);
int getInputNumber(message_t);
int getOutputNum(message_t);
void getOutputState(message_t json, char* outputStatePtr);

#endif //JSON_TESTBENCH_JSONGETTER_H
