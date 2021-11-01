cmake_minimum_required(VERSION 3.20)
project(json_testbench C)

set(CMAKE_C_STANDARD 11)

add_executable(json_testbench main.c messageLayer.c messageLayer.h jsonGetter.c jsonGetter.h ethernet.c caliper_number.h managers/Src/caliperManager.c managers/Inc/caliperManager.h managers/Src/pieceCountManager.c managers/Inc/pieceCountManager.h managers/Src/digitalOutManager.c managers/Inc/digitalOutManager.h managers/Src/analogInManager.c managers/Inc/analogInManager.h testBenchVars.h testBenchVars.c managers/Src/analogInDigitalOutManager.c managers/Inc/analogInDigitalOutManager.h)
