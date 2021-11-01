#include <stdio.h>
#include "ethernet.h"
#include "string.h"
#include "json.h"
#include <assert.h>
#include "messageLayer.h"
#include "jsonGetter.h"

int adc_measure;

char testBench[1000];

void udpClient_custom_string(message_t msg){
    strcpy(testBench, msg.msg);
}

void ETHonMessageReceived(message_t json){
    char frameType[20];
    getFrameType(json, frameType);

    if(strcmp(frameType, "READ_ANALOG_IN") == 0){
        AnalogInManager(json);
    }

    if(strcmp(frameType, "DIGITAL_OUTPUT_SET_STATE") == 0){
        DigitalOutManager(json);
    }
}


void ETHsendMessage(message_t message){
    udpClient_custom_string(message);
}

int readAdc(void){
    return adc_measure;
}

bool analogValidate(uint32_t analogData){
    return analogData <= 150 && analogData >= 50; // TODO: modify this
}

void AnalogInManager(message_t json){
    int receivedData = readAdc();
    int inputNum = getInputNumber(json);

    char str2send[100];
    bool isValid = analogValidate(receivedData);
    message_t msg = sendAnalogInMessage(str2send, inputNum, receivedData, isValid);

    ETHsendMessage(msg);
}

void DigitalOutManager(message_t json){
    int outputNum = getOutputNum(json);
    char outputState[4];
    getOutputState(json, outputState);

    if(strcmp(outputState, "ON") == 0){
        sprintf(testBench,"ON->%d", outputNum);
    }
    if(strcmp(outputState, "OFF") == 0){
        sprintf(testBench,"OFF->%d", outputNum);
    }
}

message_t json_to_msg(char * filename){
    FILE *f = fopen(filename, "rb");
    fseek(f, 0, SEEK_END);
    long fsize = ftell(f);
    fseek(f, 0, SEEK_SET);  /* same as rewind(f); */

    char *string = malloc(fsize + 1);
    fread(string, 1, fsize, f);
    fclose(f);

    string[fsize] = 0;
//    printf(string);
    message_t msg;
    msg.msg = string;
    msg.len = fsize;
    return msg;
}


int main() {

    char * filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\analogIn.json";


    message_t msg = json_to_msg(filename);

    adc_measure = 100;
    ETHonMessageReceived(msg); // {"frameType": "MEASURED_ANALOG_IN","inputNumber": 2, "analogData": 100}
    assert(strcmp(testBench, "{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": 2, \"analogData\": 100}") == 0);
    adc_measure = 200;
    ETHonMessageReceived(msg); //
    assert(strcmp(testBench, "{\"frameType\": \"RETRY_ANALOG_IN\",\"inputNumber\": 2}") == 0);

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut1.json";

    msg = json_to_msg(filename);
    ETHonMessageReceived(msg);
    assert(strcmp(testBench, "ON->2") == 0);
    //  Turning ON output number 2

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut2.json";
    msg = json_to_msg(filename);
    ETHonMessageReceived(msg);
    assert(strcmp(testBench, "OFF->1") == 0);

    char str2send[100];

    ETHsendMessage(sendCaliperWarningMessage(str2send));
    assert(strcmp(testBench, "{\"frameType\": \"WARNING_RETRY_CALIPER\"}") == 0);


    ETHsendMessage(sendCaliperMeasure(str2send,1.2342)); // TODO: must check this %f format
    assert(strcmp(testBench, "{\"frameType\": \"SEND_CALIPER_MEASURE\",\"data\": 1.234200}") == 0);

    ETHsendMessage(sendIncrementPieceCount(str2send));
    assert(strcmp(testBench, "{\"frameType\": \"INCREMENT_PIECE_COUNTER\"}") == 0);

}
