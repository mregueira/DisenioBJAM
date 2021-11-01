/*
 * ethernet.h
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */

#ifndef SRC_ETHERNET_H_
#define SRC_ETHERNET_H_

#include "../../Inc/CODE_TESTING.h"
#include "../../testBenchUtils/Inc/message.h"
#include "../../managers/Inc/analogInDigitalOutManager.h"

#ifdef TESTING
#include "../../testBenchUtils/Inc/testBenchVars.h"
#else
#include "../../Inc/udpClientRAW.h"
#endif

void ETHsendMessage(message_t message);
void ETHonMessageReceived(message_t json);


#endif /* SRC_ETHERNET_H_ */
