#include <stdio.h>
#include "../utils/Inc/ethernet.h"
#include <assert.h>
#include <string.h>
#include "../managers/Inc/caliperManager.h"
#include "../managers/Inc/pieceCountManager.h"
#include "stdlib.h"

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


int main() {

    // Managers Testing
    //  -  Caliper Manager
    // TODO: caliperManager calls should return the caliper wanted

    setValidCaliperMeasure(true);

    caliperManager(3);         // TODO: must check this %f format
    assert(strcmp(getTestBench(), "{\"frameType\": \"SEND_CALIPER_MEASURE\",\"data\": 1.234520,\"unit\": \"mm\"}") == 0);

    setValidCaliperMeasure(false);

    caliperManager(3);
    assert(strcmp(getTestBench(), "{\"frameType\": \"WARNING_RETRY_CALIPER\"}") == 0);

    //  -  Piece Count Manager
    pieceCountManager();
    assert(strcmp(getTestBench(), "{\"frameType\": \"INCREMENT_PIECE_COUNTER\"}") == 0);

    //  - Analog In Manager
    char * filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\analogIn.json";
    message_t msg = json_to_msg(filename);
    setAdcMeasure(100);
    ETHonMessageReceived(msg); // calls analogInManager(msg);
    assert(strcmp(getTestBench(), "{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": 2, \"analogData\": 100}") == 0);

    setAdcMeasure(200);
    ETHonMessageReceived(msg); // calls analogInManager(msg);
    assert(strcmp(getTestBench(), "{\"frameType\": \"RETRY_ANALOG_IN\",\"inputNumber\": 2}") == 0);

    //  - Digital Out Manager

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut1.json";

    msg = json_to_msg(filename);
    ETHonMessageReceived(msg); // calls digitalOutManager(msg);
    assert(strcmp(getTestBench(), "ON->2") == 0);

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut2.json";
    msg = json_to_msg(filename);
    ETHonMessageReceived(msg); // calls digitalOutManager(msg);
    assert(strcmp(getTestBench(), "OFF->1") == 0);
}
