//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_JSONGETTER_H
#define JSON_TESTBENCH_JSONGETTER_H

#ifdef TESTING
#include "../../../../testBenchUtils/Inc/message.h"
#else
#include "../../Comeca/Common/Inc/message.h"
#endif

//#include "../../../../ExternalLibs/JSONParser/Inc/json.h"
#include "../../../../ExternalLibs/JSONParser/Inc/cJSON.h"

void getActionType(message_t json, char* frameTypePtr);
int getInputNumber(message_t json);
int getOutputNum(message_t json);
void getOutputState(message_t json, char* outputStatePtr);

#endif //JSON_TESTBENCH_JSONGETTER_H
