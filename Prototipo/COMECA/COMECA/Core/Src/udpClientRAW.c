/*
  ***************************************************************************************************************
  ***************************************************************************************************************
  ***************************************************************************************************************

  File:		  udpClientRAW.c
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


#include "lwip/pbuf.h"
#include "lwip/udp.h"
#include "lwip/tcp.h"

#include "stdio.h"
#include "string.h"
#include "ethernet.h"
#include "udpClientRAW.h"


void udp_receive_callback(void *arg, struct udp_pcb *upcb, struct pbuf *p, const ip_addr_t *addr, u16_t port);

struct udp_pcb *upcb;
char buffer[100];
message_t message;

static void udpClient_send_first_message(void)
{
  struct pbuf *txBuf;
  char data[100];

  int len = sprintf(data, "Success on Initializing UDP Client");

  /* allocate pbuf from pool*/
  txBuf = pbuf_alloc(PBUF_TRANSPORT, len, PBUF_RAM);

  if (txBuf != NULL)
  {
    /* copy data to pbuf */
    pbuf_take(txBuf, data, len);

    /* send udp data */
    udp_send(upcb, txBuf);

    /* free pbuf */
    pbuf_free(txBuf);
  }
}


void udpClient_connect(void)
{
	err_t err;

	/* 1. Create a new UDP control block  */
	upcb = udp_new();

	/* Bind the block to module's IP and port */
	ip_addr_t myIPaddr;
	IP_ADDR4(&myIPaddr, 192, 168, 0, 111);
	udp_bind(upcb, &myIPaddr, 8);


	/* configure destination IP address and port */
	ip_addr_t DestIPaddr;
	IP_ADDR4(&DestIPaddr, 192, 168, 0, 25);
	err= udp_connect(upcb, &DestIPaddr, 7);

	if (err == ERR_OK)
	{
//		/* 2. Send message to server */
		udpClient_send_first_message ();

		/* 3. Set a receive callback for the upcb */
		udp_recv(upcb, udp_receive_callback, NULL);
	}
}


void udpClient_custom_string(message_t message)
{
  struct pbuf *txBuf;
  char data[100];

	/* Copy the message data to the txBuf */
  strncpy (data, message.msg, message.len);
  int len = message.len;

  /* allocate pbuf from pool*/
  txBuf = pbuf_alloc(PBUF_TRANSPORT, len, PBUF_RAM);

  if (txBuf != NULL)
  {
    /* copy data to pbuf */
    pbuf_take(txBuf, data, len);

    /* send udp data */
    udp_send(upcb, txBuf);

    /* free pbuf */
    pbuf_free(txBuf);
  }
}


void udp_receive_callback(void *arg, struct udp_pcb *upcb, struct pbuf *p, const ip_addr_t *addr, u16_t port)
{
	/* Copy the data from the pbuf */
	strncpy (buffer, (char *)p->payload, p->len);

	// store the Ethernet Message
	message.msg = buffer;
	message.len = p-> len;

	// process the received message
	ETHonMessageReceived(message);

	/* Free receive pbuf */
	pbuf_free(p);
}





