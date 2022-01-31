//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_PIECECOUNTMANAGER_H
#define JSON_TESTBENCH_PIECECOUNTMANAGER_H

#include "../../Common/Inc/BUFFER_SIZE.h"

#ifdef TESTING
#include "../../../testBenchUtils/Inc/message.h"
#else
#include "../../Common/Inc/message.h"
#endif

#include "../../Messages/Send/Inc/messageLayer.h"

void pieceCountManager(void);

#endif //JSON_TESTBENCH_PIECECOUNTMANAGER_H
