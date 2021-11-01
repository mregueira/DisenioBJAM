//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_MESSAGELAYER_H
#define JSON_TESTBENCH_MESSAGELAYER_H

#include "stdbool.h"
#include "message.h"
#include "stdio.h"

#define TESTING

#ifdef TESTING
typedef struct {
    float number;
    bool unit;
}digimatic_measure_t;
#endif

message_t sendCaliperMeasure(char * str2send, digimatic_measure_t measure);
message_t sendIncrementPieceCount(char * str2send);
message_t sendAnalogInMessage(char * str2send, int inputNum, int receivedData, bool isValid);
message_t sendCaliperWarning(char * str2send);

#endif //JSON_TESTBENCH_MESSAGELAYER_H