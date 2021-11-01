//
// Created by joa-m on 11/1/2021.
//
#include "ethernet.h"
#include "managers/Inc/analogInDigitalOutManager.h"

#ifdef TESTING
    char testBench[1000];

    // this should come from udpClientRAW.h
    void udpClient_custom_string(message_t msg){
        strcpy(testBench, msg.msg);
    }

#endif

void ETHsendMessage(message_t message){
    udpClient_custom_string(message);
}

void ETHonMessageReceived(message_t json){
    analogInDigitalOutManager(json);
}