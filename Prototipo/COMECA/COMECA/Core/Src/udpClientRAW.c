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
<<<<<<< HEAD
#include "../utils/Inc/ethernet.h"
=======

>>>>>>> master
#include "udpClientRAW.h"


void udp_receive_callback(void *arg, struct udp_pcb *upcb, struct pbuf *p, const ip_addr_t *addr, u16_t port);
<<<<<<< HEAD

struct udp_pcb *upcb;
char buffer[1000];
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
=======
static void udpClient_send(void);

struct udp_pcb *upcb;
char buffer[100];
int counter = 0;

extern TIM_HandleTypeDef htim2;


void PseudoHAL_TIM_PeriodElapsedCallback(TIM_HandleTypeDef *htim)
{
	udpClient_send();
}


/* IMPLEMENTATION FOR UDP CLIENT :   source:https://www.geeksforgeeks.org/udp-server-client-implementation-c/

1. Create UDP socket.
2. Send message to server.
3. Wait until response from server is received.
4. Process reply and go back to step 2, if necessary.
5. Close socket descriptor and exit.
*/
>>>>>>> master


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
<<<<<<< HEAD
	IP_ADDR4(&DestIPaddr, 192, 168, 0, 22); // CAMBIAR ESTO SIEMRPE!
=======
	IP_ADDR4(&DestIPaddr, 192, 168, 0, 25);
>>>>>>> master
	err= udp_connect(upcb, &DestIPaddr, 7);

	if (err == ERR_OK)
	{
<<<<<<< HEAD
//		/* 2. Send message to server */
		udpClient_send_first_message ();
=======
		/* 2. Send message to server */
		udpClient_send ();
>>>>>>> master

		/* 3. Set a receive callback for the upcb */
		udp_recv(upcb, udp_receive_callback, NULL);
	}
}

<<<<<<< HEAD

void udpClient_custom_string(message_t message)
=======
static void udpClient_send(void)
>>>>>>> master
{
  struct pbuf *txBuf;
  char data[100];

<<<<<<< HEAD
	/* Copy the message data to the txBuf */
  strncpy (data, message.msg, message.len);
  int len = message.len;
=======
  int len = sprintf(data, "sending UDP client message %d", counter);
>>>>>>> master

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

<<<<<<< HEAD
	// store the Ethernet Message
	message.msg = buffer;
	message.len = p-> len;

	// process the received message
	ETHonMessageReceived(message);
=======
	/*increment message count */
	counter++;
>>>>>>> master

	/* Free receive pbuf */
	pbuf_free(p);
}


<<<<<<< HEAD



=======
>>>>>>> master
