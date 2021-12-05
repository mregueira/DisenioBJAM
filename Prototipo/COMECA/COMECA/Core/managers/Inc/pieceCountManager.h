//
// Created by joa-m on 11/1/2021.
//

#ifndef JSON_TESTBENCH_PIECECOUNTMANAGER_H
#define JSON_TESTBENCH_PIECECOUNTMANAGER_H

#include "../../Inc/CODE_TESTING.h"

#ifdef TESTING
#include "../../testBenchUtils/Inc/message.h"
#else
#include "../../Inc/message.h"
#endif

#include "../../utils/Inc/messageLayer.h"
#include "../../utils/Inc/ethernet.h"

void pieceCountManager(void);

#endif //JSON_TESTBENCH_PIECECOUNTMANAGER_H
