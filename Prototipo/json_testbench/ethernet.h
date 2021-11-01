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
#include "jsonGetter.h"
#include "string.h"
#include "caliper_number.h"

#include "managers/Inc/analogInManager.h"
#include "managers/Inc/digitalOutManager.h"

void caliperManager(caliper_number caliperNumber);
void pieceCountManager(void);
void ETHonMessageReceived(message_t json);
void ETHsendMessage(message_t message);


#endif /* SRC_ETHERNET_H_ */
