//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/ethernet.h"


#ifdef TESTING
    // this should come from udpClientRAW.h
    void udpClient_custom_string(message_t msg){
        strcpy(getTestBench(), msg.msg);
        printf(getTestBench());
        printf("\n");
    }
#endif

void ETHsendMessage(message_t message){
    udpClient_custom_string(message);
}

void ETHonMessageReceived(message_t json){
    analogInDigitalOutManager(json);
}
