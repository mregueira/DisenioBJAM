/*
 * ethernet.h
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */

#ifndef SRC_ETHERNET_H_
#define SRC_ETHERNET_H_

#include "message.h"
#include "stdlib.h" // itoa
#include "stdbool.h"
#include "stdint.h" // for uintx_t

#define DELIMITER ':'
#define FRAME_CHARS 15
#define DATA_CHARS 15
#define DELIMITER_CHARS 1

void ETHonMessageReceived(message_t message);
void ETHsendMessage(message_t message);

void AnalogInManager(message_t);
void DigitalOutManager(message_t);


#endif /* SRC_ETHERNET_H_ */
