//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_JSONGETTER_H
#define JSON_TESTBENCH_JSONGETTER_H

#include "message.h"
#include "json.h"

const char * getFrameType(struct json_value_s*, message_t);
int getInputNumber(struct json_value_s*, message_t);
int getOutputNum(struct json_value_s*, message_t);
const char * getOutputState(struct json_value_s*, message_t);

#endif //JSON_TESTBENCH_JSONGETTER_H
