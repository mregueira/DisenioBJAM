//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/pieceCountManager.h"

void pieceCountManager(void){
    char str2send[GLOBAL_MAX_STRING_SIZE];
    message_t msg;
    msg = sendIncrementPieceCount(str2send);
    ETHsendMessage(msg);
}
