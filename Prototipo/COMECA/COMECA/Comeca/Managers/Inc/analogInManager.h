//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_ANALOGINMANAGER_H
#define JSON_TESTBENCH_ANALOGINMANAGER_H



#define ADC_CH0_MASK 0b10010111		//0b10010111
#define ADC_CH1_MASK 0b11010111
#define ADC_CH2_MASK 0b10100111
#define ADC_CH3_MASK 0b11100111


#include "../../../TestingResources/Inc/CODE_TESTING.h"
#include "../../Common/Inc/BUFFER_SIZE.h"

#ifdef TESTING
#include "../../testBenchUtils/Inc/testBenchVars.h"
#include "../../testBenchUtils/Inc/message.h"
#else
#include "../../Common/Inc/message.h"

#endif

#include "stdbool.h"
#include "../../Messages/Receive/Inc/jsonGetter.h"
#include "../../Messages/Send/Inc/messageLayer.h"
#include "../../EthernetEvent/Inc/ethernet.h"

#define MIN_VALID_VALUE 4 // todo: this will probably change
#define MAX_VALID_VALUE 20

void analogInManager(message_t json);

#endif //JSON_TESTBENCH_ANALOGINMANAGER_H
