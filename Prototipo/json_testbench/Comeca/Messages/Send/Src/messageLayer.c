//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/messageLayer.h"

message_t sendCaliperMeasure(char * str2send, digimatic_measure_t measure, caliper_number caliperNumber){
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
    return msg2send;
}

message_t sendCaliperWarning(char * str2send, caliper_number caliperNumber){
    int len = sprintf(str2send,
    		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": %d, \"warning\": \"retryCaliperMeasure\"}}",caliperNumber);

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendIncrementPieceCount(char * str2send){
    int len = sprintf(str2send,
    		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"contarPieza\"}");

    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendAnalogInMessage(char * str2send, int inputNum, float receivedData, bool isValid){
    int len = -1;
    if(isValid){ // si es valido
        len = sprintf(str2send,
        		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": %d, \"measure\": %.5f}}",inputNum, receivedData);

    }else{
        len = sprintf(str2send,
        		"{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": %d, \"warning\": \"retryAnalogIn\"}}", inputNum);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}
