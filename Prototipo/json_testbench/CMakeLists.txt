cmake_minimum_required(VERSION 3.20)
project(json_testbench C)

set(CMAKE_C_STANDARD 11)

if (CMAKE_BUILD_TYPE MATCHES Debug)
    add_definitions(-DTESTING)
endif()

add_executable(json_testbench main.c
        Comeca/Messages/Send/Src/messageLayer.c Comeca/Messages/Send/Inc/messageLayer.h
        Comeca/Messages/Receive/Src/jsonGetter.c Comeca/Messages/Receive/Inc/jsonGetter.h
        Comeca/EthernetEvent/Src/ethernet.c Comeca/EthernetEvent/Inc/ethernet.h
        Comeca/Managers/Src/caliperManager.c  Comeca/Managers/Inc/caliperManager.h
        Comeca/Managers/Src/pieceCountManager.c  Comeca/Managers/Inc/pieceCountManager.h
        Comeca/Managers/Src/digitalOutManager.c  Comeca/Managers/Inc/digitalOutManager.h
        Comeca/Managers/Src/analogInManager.c  Comeca/Managers/Inc/analogInManager.h
        Comeca/DigimaticProtocol/Src/digimatic.c Comeca/DigimaticProtocol/Inc/digimatic.h
        Comeca/Common/Inc/BUFFER_SIZE.h
        testBenchUtils/Inc/testBenchVars.h testBenchUtils/Src/testBenchVars.c
        testBenchUtils/Inc/digimatic_measure_t.h
        testBenchUtils/Inc/caliper_number.h)
