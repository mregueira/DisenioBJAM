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

message_t sendCaliperMeasure(char * str2send, digimatic_measure_t measure, caliper_number caliperNumber){
    int len = -1;
    if(measure.unit){
        len = sprintf(str2send,"{\"frameType\": \"SEND_CALIPER_MEASURE\",\"caliperNumber\": %d,\"data\": %.5f,\"unit\": \"inch\"}",caliperNumber, measure.number);
    } else {
        len = sprintf(str2send,"{\"frameType\": \"SEND_CALIPER_MEASURE\",\"caliperNumber\": %d,\"data\": %.5f,\"unit\": \"mm\"}",caliperNumber, measure.number);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendCaliperWarning(char * str2send, caliper_number caliperNumber){
    int len = sprintf(str2send,"{\"frameType\": \"WARNING_RETRY_CALIPER\" ,\"caliperNumber\": %d}", caliperNumber);
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendIncrementPieceCount(char * str2send){
    int len = sprintf(str2send,"{\"frameType\": \"INCREMENT_PIECE_COUNTER\"}");
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

message_t sendAnalogInMessage(char * str2send, int inputNum, float receivedData, bool isValid){
    int len = -1;
    if(isValid){ // si es valido
        len = sprintf(str2send,"{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": %d, \"analogData\": %.5f}", inputNum, receivedData);
    }else{
        len = sprintf(str2send,"{\"frameType\": \"RETRY_ANALOG_IN\",\"inputNumber\": %d}", inputNum);
    }
    message_t msg2send;
    msg2send.msg = str2send;
    msg2send.len = len;
    return msg2send;
}

