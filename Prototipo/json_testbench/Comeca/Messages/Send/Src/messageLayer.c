//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/messageLayer.h"


void sendCaliperMeasure(digimatic_measure_t measure, caliper_number caliperNumber){
    char str2send[GLOBAL_MAX_STRING_SIZE];
    int len = -1;
    if(measure.unit){
        len = sprintf(str2send,
        		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": %d, \"measure\": %.5f, \"unit\": \"inches\"}}",caliperNumber, measure.number);
    } else {
        len = sprintf(str2send,
        		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": %d, \"measure\": %.5f, \"unit\": \"mm\"}}",caliperNumber, measure.number);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;

    ETHsendMessage(msg2send);
}

void sendCaliperWarning(caliper_number caliperNumber){
    char str2send[GLOBAL_MAX_STRING_SIZE];
    int len = sprintf(str2send,
    		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": %d, \"warning\": \"retryCaliperMeasure\"}}",caliperNumber);

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;

    ETHsendMessage(msg2send);
}

void sendIncrementPieceCount(void){
    char str2send[GLOBAL_MAX_STRING_SIZE];

    int len = sprintf(str2send,
    		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"contarPieza\"}");

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;

    ETHsendMessage(msg2send);
}

void sendAnalogInMessage(int inputNum, float receivedData){
    char str2send[GLOBAL_MAX_STRING_SIZE];

    int len = sprintf(str2send,
    		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": %d, \"measure\": %.5f}}",inputNum, receivedData);

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;

    ETHsendMessage(msg2send);
}

void sendAnalogInWarning(int inputNum){
    char str2send[GLOBAL_MAX_STRING_SIZE];

	int len = sprintf(str2send,
    	"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": %d, \"warning\": \"retryAnalogIn\"}}", inputNum);

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;

    ETHsendMessage(msg2send);
}
