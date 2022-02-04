/*
  ***************************************************************************************************************
  ***************************************************************************************************************
  ***************************************************************************************************************

  File:		  udpServerRAW.h
  Author:     ControllersTech.com
  Updated:    Jul 23, 2021

  ***************************************************************************************************************
  Copyright (C) 2017 ControllersTech.com

  This is a free software under the GNU license, you can redistribute it and/or modify it under the terms
  of the GNU General Public License version 3 as published by the Free Software Foundation.
  This software library is shared with public for educational purposes, without WARRANTY and Author is not liable for any damages caused directly
  or indirectly by this software, read more about this on the GNU General Public License.

  ***************************************************************************************************************
*/


#ifndef INC_UDPSERVERRAW_H_
#define INC_UDPSERVERRAW_H_

#include "../../Comeca/Common/Inc/BUFFER_SIZE.h"
#include "../../Comeca/Common/Inc/message.h"

void udpServer_init(void);
void udp_send_message(message_t message);


#endif /* INC_UDPSERVERRAW_H_ */