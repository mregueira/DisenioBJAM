/*
  ***************************************************************************************************************
  ***************************************************************************************************************
  ***************************************************************************************************************

  File:		  udpServerRAW.c
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
#include "../Inc/udpServerRAW.h"
#include "../../Comeca/EthernetEvent/Inc/ethernet.h"


struct udp_pcb *local_upcb;
char buffer[GLOBAL_MAX_STRING_SIZE];
message_t message;

void udp_receive_callback(void *arg, struct udp_pcb *upcb, struct pbuf *p, const ip_addr_t *addr, u16_t port);


/* IMPLEMENTATION FOR UDP Server :   source:https://www.geeksforgeeks.org/udp-server-client-implementation-c/

1. Create UDP socket.
2. Bind the socket to server address.
3. Wait until datagram packet arrives from client.
4. Process the datagram packet and send a reply to client.
5. Go back to Step 3.
*/

void udpServer_init(void)
{
	// UDP Control Block structure
   err_t err;

   /* 1. Create a new UDP control block  */
   local_upcb = udp_new();

	/* Bind the block to module's IP and port */
	ip_addr_t myIPaddr;
	IP_ADDR4(&myIPaddr, 192, 168, 1, 42);
	udp_bind(local_upcb, &myIPaddr, 4444);


	/* configure destination IP address and port */
	ip_addr_t DestIPaddr;
	IP_ADDR4(&DestIPaddr, 192, 168, 1, 10);
	err= udp_connect(local_upcb, &DestIPaddr, 4445);


//   err = udp_bind(local_upcb, &myIPADDR, 4444);  // 7 is the server UDP port

   /* 3. Set a receive callback for the upcb */
   if(err == ERR_OK)
   {
	   udp_recv(local_upcb, udp_receive_callback, NULL);
   }
   else
   {
	   udp_remove(local_upcb);
	   assert(0);
   }
}

// udp_receive_callback will be called, when the client sends some data to the server
/* 4. Process the datagram packet and send a reply to client. */

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

void udp_send_message(message_t message){
	struct pbuf *txBuf;
	char data[GLOBAL_MAX_STRING_SIZE];

		/* Copy the message data to the txBuf */
	strncpy (data, message.msg, message.len);
	int len = message.len;

	/* allocate pbuf from RAM*/
	txBuf = pbuf_alloc(PBUF_TRANSPORT,len, PBUF_RAM);

	pbuf_take(txBuf, data, len);

//	/* Send a Reply to the Client */
	err_t err2 = udp_send(local_upcb, txBuf);

	/* Free the p_tx buffer */
	pbuf_free(txBuf);
}
