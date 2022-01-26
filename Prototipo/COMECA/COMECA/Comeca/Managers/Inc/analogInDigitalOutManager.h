//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_ANALOGINDIGITALOUTMANAGER_H
#define JSON_TESTBENCH_ANALOGINDIGITALOUTMANAGER_H


#include "../../../TestingResources/Inc/CODE_TESTING.h"

#ifdef TESTING
#include "../../testBenchUtils/Inc/message.h"
#else
#include "../../Common/Inc/message.h"
#endif

#include "../../Messages/Send/Inc/messageLayer.h"
#include "../../Messages/Receive/Inc/jsonGetter.h"

#include "../../../Comeca/Managers/Inc/analogInManager.h"
#include "../../../Comeca/Managers/Inc/digitalOutManager.h"

void analogInDigitalOutManager(message_t json);

#endif //JSON_TESTBENCH_ANALOGINDIGITALOUTMANAGER_H
