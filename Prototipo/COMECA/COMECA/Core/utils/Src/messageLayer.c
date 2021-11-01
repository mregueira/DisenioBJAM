//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/messageLayer.h"

message_t sendCaliperWarningMessage(char * str2send){
    int len = sprintf(str2send,"{\"frameType\": \"WARNING_RETRY_CALIPER\"}");
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
};

message_t sendCaliperMeasure(char * str2send, digimatic_measure_t measure){
    int len = -1;
    if(measure.unit){
        len = sprintf(str2send,"{\"frameType\": \"SEND_CALIPER_MEASURE\",\"data\": %f,\"unit\": \"inch\"}", measure.number);
    } else {
        len = sprintf(str2send,"{\"frameType\": \"SEND_CALIPER_MEASURE\",\"data\": %f,\"unit\": \"mm\"}", measure.number);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
};

message_t sendIncrementPieceCount(char * str2send){
    int len = sprintf(str2send,"{\"frameType\": \"INCREMENT_PIECE_COUNTER\"}");
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendAnalogInMessage(char * str2send, int inputNum, int receivedData, bool isValid){
    int len = -1;
    if(isValid){ // si es valido
        len = sprintf(str2send,"{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": %d, \"analogData\": %d}", inputNum, receivedData);
    }else{
        len = sprintf(str2send,"{\"frameType\": \"RETRY_ANALOG_IN\",\"inputNumber\": %d}", inputNum);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendCaliperWarning(char * str2send){
    int len = sprintf(str2send,"{\"frameType\": \"WARNING_RETRY_CALIPER\"}");
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}
