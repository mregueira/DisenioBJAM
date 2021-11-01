//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/pieceCountManager.h"

void pieceCountManager(){
    char str2send[100];
    message_t msg;
    msg = sendIncrementPieceCount(str2send);
    ETHsendMessage(msg);
}