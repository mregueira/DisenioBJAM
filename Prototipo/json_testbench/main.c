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

int readAdc(void){
    return adc_measure;
}

bool analogValidate(uint32_t analogData){
    return analogData <= 150 && analogData >= 50; // TODO: modify this
}


void analogInManager(message_t json){
    int receivedData = readAdc();
    int inputNum = getInputNumber(json);

    char str2send[100];
    bool isValid = analogValidate(receivedData);
    message_t msg = sendAnalogInMessage(str2send, inputNum, receivedData, isValid);

    ETHsendMessage(msg);
}

void digitalOutManager(message_t json){
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

void ETHonMessageReceived(message_t json){
    char frameType[20];
    getFrameType(json, frameType);

    if(strcmp(frameType, "READ_ANALOG_IN") == 0){
        analogInManager(json);
    }

    if(strcmp(frameType, "DIGITAL_OUTPUT_SET_STATE") == 0){
        digitalOutManager(json);
    }
}

void ETHsendMessage(message_t message){
    udpClient_custom_string(message);
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
    message_t msg;
    msg.msg = string;
    msg.len = fsize;
    return msg;
}

#define RETRY_TIMES 3
typedef enum {
    CALIPER_1,
    CALIPER_2,
    CALIPER_3,
    CALIPER_4
} caliper_number;


typedef uint8_t digimatic_frame_t; // every frame needs 4 bits.

bool isValidCaliperMeasure = true;

bool validCaliperMeasure(digimatic_frame_t* digimaticFrame){
    return isValidCaliperMeasure;
}

digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper){
    return NULL;
}

double digimatic2Double(digimatic_frame_t* digimaticFrame){
    return 1.25234;
}

void caliperManager(caliper_number caliperNumber){
    int retry;
    bool isValid = false;
    digimatic_frame_t* digimaticFrame;
    for(retry = 0; retry<RETRY_TIMES; retry++){
        digimaticFrame = digimaticGetMeasure(caliperNumber);
        isValid = validCaliperMeasure(digimaticFrame);
        if(isValid) break;
    }

    char str2send[100];
    message_t msg;
    if(isValid){
        msg = sendCaliperMeasure(str2send, digimatic2Double(digimaticFrame));
    } else {
        msg = sendCaliperWarning(str2send);
    }

    ETHsendMessage(msg);
}

void pieceCountManager(){
    char str2send[100];
    message_t msg;
    msg = sendIncrementPieceCount(str2send);
    ETHsendMessage(msg);
}

int main() {

    // Managers Testing
    //  -  Caliper Manager
    // TODO: caliperManager calls should return the caliper wanted

    caliperManager(CALIPER_3);         // TODO: must check this %f format
    assert(strcmp(testBench, "{\"frameType\": \"SEND_CALIPER_MEASURE\",\"data\": 1.252340}") == 0);

    isValidCaliperMeasure = false;

    caliperManager(CALIPER_3);
    assert(strcmp(testBench, "{\"frameType\": \"WARNING_RETRY_CALIPER\"}") == 0);

    //  -  Piece Count Manager
    pieceCountManager();
    assert(strcmp(testBench, "{\"frameType\": \"INCREMENT_PIECE_COUNTER\"}") == 0);

    //  - Analog In Manager
    char * filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\analogIn.json";
    message_t msg = json_to_msg(filename);
    adc_measure = 100;
    ETHonMessageReceived(msg);
//    analogInManager(msg); // {"frameType": "MEASURED_ANALOG_IN","inputNumber": 2, "analogData": 100}
    assert(strcmp(testBench, "{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": 2, \"analogData\": 100}") == 0);

    adc_measure = 200;
    ETHonMessageReceived(msg);
//    analogInManager(msg);
    assert(strcmp(testBench, "{\"frameType\": \"RETRY_ANALOG_IN\",\"inputNumber\": 2}") == 0);

    //  - Digital Out Manager

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut1.json";

    msg = json_to_msg(filename);
    ETHonMessageReceived(msg);
//    digitalOutManager(msg);
    assert(strcmp(testBench, "ON->2") == 0);

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut2.json";
    msg = json_to_msg(filename);
    ETHonMessageReceived(msg);
//    digitalOutManager(msg);
    assert(strcmp(testBench, "OFF->1") == 0);
}
