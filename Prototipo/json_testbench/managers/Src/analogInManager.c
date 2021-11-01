//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/analogInManager.h"

#ifdef TESTING
    // these functions should come from analogInManager.h
    int readAdc(int inputNum){
        return getAdcMeasure();
    }

#endif

bool analogValidate(uint32_t analogData){
    return analogData <= MAX_VALID_VALUE && analogData >= MIN_VALID_VALUE;
}

void analogInManager(message_t json){
    int inputNum = getInputNumber(json);
    int receivedData = readAdc(inputNum);


    char str2send[100];
    bool isValid = analogValidate(receivedData);
    message_t msg = sendAnalogInMessage(str2send, inputNum, receivedData, isValid);

    ETHsendMessage(msg);
}