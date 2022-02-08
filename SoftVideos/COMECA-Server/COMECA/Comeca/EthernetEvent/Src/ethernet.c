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
//    udpClient_custom_string(message);
}

void ETHonMessageReceived(message_t json){
    char actionType[GLOBAL_MAX_STRING_SIZE];
    getActionType(json, actionType);

    if(strcmp(actionType, "getAnalogIn") == 0){
        analogInManager(json);
    }

    if(strcmp(actionType, "setSalidaDigital") == 0){
        digitalOutManager(json);
    }
}
