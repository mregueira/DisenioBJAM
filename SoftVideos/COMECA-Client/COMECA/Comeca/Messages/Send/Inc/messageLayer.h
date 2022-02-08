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
#include "../../../Common/Inc/BUFFER_SIZE.h"
#include "../../../EthernetEvent/Inc/ethernet.h"
#else
#include "../../Comeca/DigimaticProtocol/Inc/digimatic.h"
#include "../../Comeca/Common/Inc/message.h"
#include "../../../EthernetEvent/Inc/ethernet.h"
#endif

void sendCaliperMeasure(digimatic_measure_t measure, caliper_number caliperNumber);
void sendCaliperWarning(caliper_number caliperNumber);

void sendIncrementPieceCount(void);
void sendAnalogInMessage(int inputNum, float receivedData);
void sendAnalogInWarning(int inputNum);

#endif //JSON_TESTBENCH_MESSAGELAYER_H
