#include <stdio.h>
#include "Comeca/EthernetEvent/Inc/ethernet.h"
#include <assert.h>
#include <string.h>
#include "Comeca/Managers/Inc/caliperManager.h"
#include "Comeca/Managers/Inc/pieceCountManager.h"
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
    setValidCaliperMeasure(true);

    setDigimaticMeasure(1.23452, false);
    caliperManager(3);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": 3, \"measure\": 1.23452, \"unit\": \"mm\"}}") == 0);

    setDigimaticMeasure(-10.54355, true);
    caliperManager(3);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": 3, \"measure\": -10.54355, \"unit\": \"inches\"}}") == 0);

    setValidCaliperMeasure(false);

    caliperManager(3);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": 3, \"warning\": \"retryCaliperMeasure\"}}") == 0);

    //  -  Piece Count Manager
    pieceCountManager();
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"contarPieza\"}") == 0);

    //  - Analog In Manager
    char * filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\analogIn.json";
    message_t msg = json_to_msg(filename);
    setAdcMeasure(1000);
    ETHonMessageReceived(msg); // calls analogInManager(msg);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": 2, \"measure\": 5.37109}}") == 0);

    setAdcMeasure(200);
    ETHonMessageReceived(msg); // calls analogInManager(msg);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": 2, \"warning\": \"retryAnalogIn\"}}") == 0);

    setAdcMeasure(10000);
    ETHonMessageReceived(msg); // calls analogInManager(msg);
    assert(strcmp(getTestBench(), "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoEntradaAnalogica\", \"data\": {\"inputNumber\": 2, \"warning\": \"retryAnalogIn\"}}") == 0);


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
