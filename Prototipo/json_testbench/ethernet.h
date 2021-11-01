/*
 * ethernet.h
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */

#ifndef SRC_ETHERNET_H_
#define SRC_ETHERNET_H_

#define TESTING

#include "message.h"

#ifdef TESTING
#include "testBenchVars.h"
#endif

void ETHsendMessage(message_t message);
void ETHonMessageReceived(message_t json);


#endif /* SRC_ETHERNET_H_ */
