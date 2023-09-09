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
	udp_send_message(message);
}

void ETHonMessageReceived(message_t json){
    char frameType[GLOBAL_MAX_STRING_SIZE];
    getActionType(json, frameType);

    if(strcmp(frameType, "READ_ANALOG_IN") == 0){
        analogInManager(json);
    }

    if(strcmp(frameType, "setSalidaDigital") == 0){
        digitalOutManager(json);
    }
}
