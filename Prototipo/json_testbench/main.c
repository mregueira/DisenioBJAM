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
    assert(strcmp(getTestBench(),
    "{\"version\": \"0.0\", \"sequence\": 0, \"command\": \"request\",  \"action\": \"guardarDatoCalibre\", \"data\": {\"caliperNumber\": 3, \"measure\": 1.23452, \"unit\": \"mm\"}}") == 0);

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
    assert(strcmp(getTestBench(), "ON->1") == 0);

    filename = "C:\\Github_Repos\\DisenioBJAM\\Prototipo\\json_testbench\\jsons\\received\\digitalOut2.json";
    msg = json_to_msg(filename);
    ETHonMessageReceived(msg); // calls digitalOutManager(msg);
    assert(strcmp(getTestBench(), "OFF->3") == 0);

    // -  Digimatic testing

    uint8_t digFrames[13] = {
//  "ALL F"
            15,
            15,
            15,
            15,
// sign +
            0,
// digits
            1,
            2,
            0,
            0,
            0,
            0,
// decimal point
            4,
            0
    };

// valid positive
    assert(validCaliperMeasure(digFrames));
    digimatic_measure_t measure = digimaticMeasure(digFrames);
    printf("%f\n", measure.number);
    assert(measure.number == 12.000000);
    assert(measure.unit == 0);

// valid negative and inches
    digFrames[4] = 8;
    digFrames[12] = 1;
    assert(validCaliperMeasure(digFrames));
    measure = digimaticMeasure(digFrames);
    printf("%f\n", measure.number);
    assert(measure.number == -12.000000);
    assert(measure.unit == 1);

// invalid -> the first frame is not 15 (1111)
    digFrames[0] = 0;
    assert(!validCaliperMeasure(digFrames));
    digFrames[0] = 15;
// invalid -> sign is not 0 or 8
    digFrames[4] = 1;
    assert(!validCaliperMeasure(digFrames));
    digFrames[4] = 0;
//    invalid -> digit greater than 9
    digFrames[5] = 11;
    assert(!validCaliperMeasure(digFrames));
    digFrames[5] = 0;
//    invalid -> decimal point greater than 6
    digFrames[11] = 6;
    assert(!validCaliperMeasure(digFrames));
    digFrames[11] = 2;
//    invalid -> unit not 0 or 1
    digFrames[12] = 6;
    assert(!validCaliperMeasure(digFrames));
}
