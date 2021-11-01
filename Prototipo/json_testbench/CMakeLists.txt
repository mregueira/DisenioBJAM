cmake_minimum_required(VERSION 3.20)
project(json_testbench C)

set(CMAKE_C_STANDARD 11)

add_executable(json_testbench Src/main.c utils/Src/messageLayer.c utils/Inc/messageLayer.h utils/Src/jsonGetter.c utils/Inc/jsonGetter.h utils/Src/ethernet.c testBenchUtils/Inc/caliper_number.h managers/Src/caliperManager.c managers/Inc/caliperManager.h managers/Src/pieceCountManager.c managers/Inc/pieceCountManager.h managers/Src/digitalOutManager.c managers/Inc/digitalOutManager.h managers/Src/analogInManager.c managers/Inc/analogInManager.h testBenchUtils/Inc/testBenchVars.h testBenchUtils/Src/testBenchVars.c managers/Src/analogInDigitalOutManager.c managers/Inc/analogInDigitalOutManager.h Inc/CODE_TESTING.h)
