/*
 * ethernet.h
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */

#ifndef SRC_ETHERNET_H_
#define SRC_ETHERNET_H_


#ifdef TESTING
#include "../../../testBenchUtils/Inc/message.h"
#include "../../../testBenchUtils/Inc/testBenchVars.h"
#else
#include "../../Common/Inc/message.h"
#include "../../ExternalLibs/UDPClient/Inc/udpClientRAW.h"
#endif

#include "../../Messages/Send/Inc/messageLayer.h"
#include "../../Messages/Receive/Inc/jsonGetter.h"

#include "../../../Comeca/Managers/Inc/analogInManager.h"
#include "../../../Comeca/Managers/Inc/digitalOutManager.h"


void ETHsendMessage(message_t message);
void ETHonMessageReceived(message_t json);


#endif /* SRC_ETHERNET_H_ */
