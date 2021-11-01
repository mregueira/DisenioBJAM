cmake_minimum_required(VERSION 3.20)
project(json_testbench C)

set(CMAKE_C_STANDARD 11)

add_executable(json_testbench main.c messageLayer.c messageLayer.h jsonGetter.c jsonGetter.h)
