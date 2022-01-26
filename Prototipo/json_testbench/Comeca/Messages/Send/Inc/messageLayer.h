//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_MESSAGELAYER_H
#define JSON_TESTBENCH_MESSAGELAYER_H

#include "stdbool.h"

#include "stdio.h"

#ifdef TESTING
#include "../../../../testBenchUtils/Inc/message.h"
#include "../../../../testBenchUtils/Inc/caliper_number.h"
#include "../../../../testBenchUtils/Inc/digimatic_measure_t.h"
#else
#include "../../Comeca/DigimaticProtocol/Inc/digimatic.h"
#include "../../Comeca/Common/Inc/message.h"
#endif

message_t sendCaliperMeasure(char * str2send, digimatic_measure_t measure, caliper_number caliperNumber);
message_t sendCaliperWarning(char * str2send, caliper_number caliperNumber);

message_t sendIncrementPieceCount(char * str2send);
message_t sendAnalogInMessage(char * str2send, int inputNum, float receivedData, bool isValid);


#endif //JSON_TESTBENCH_MESSAGELAYER_H
