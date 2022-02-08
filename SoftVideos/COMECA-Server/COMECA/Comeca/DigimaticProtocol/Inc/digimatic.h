/*
 * digimatic.h
 *
 *  Created on: Sep 20, 2021
 *      Author: joa-m
 */

#ifndef INC_DIGIMATIC_H_
#define INC_DIGIMATIC_H_

#include "stdint.h" // for uint8_t
#include <string.h> // for memset
#include "stdbool.h"
#include <math.h>


typedef uint8_t digimatic_frame_t; // every frame needs 4 bits.

#ifndef TESTING

#include "main.h"
#define BITS_PER_FRAME 4
#define NUMBER_OF_CALIPERS 4


#define DIG_OUT_1_PORT_AND_PIN GPIOC,GPIO_PIN_9
#define DIG_OUT_1(x) DIG_OUT_1_PORT_AND_PIN,x

#define DIG_OUT_2_PORT_AND_PIN GPIOC,GPIO_PIN_7
#define DIG_OUT_2(x) DIG_OUT_2_PORT_AND_PIN,x

#define DIG_OUT_3_PORT_AND_PIN GPIOD,GPIO_PIN_15
#define DIG_OUT_3(x) DIG_OUT_3_PORT_AND_PIN,x

#define DIG_OUT_4_PORT_AND_PIN GPIOD,GPIO_PIN_13
#define DIG_OUT_4(x) DIG_OUT_4_PORT_AND_PIN,x



typedef enum {
	CALIPER_1,
	CALIPER_2,
	CALIPER_3,
	CALIPER_4
} caliper_number;

typedef enum{ // (!) don't change the order
	IDLE,	 // ONCE IT'S READ --> IDLE
	START, // SEND REQUEST --> SET THIS STATE; WHEN ENTERING THE CLOCK INTERRUPT FUNCTION, WE GET THE FIRST FRAME, WE SET THE STATE TO GETTING FRAMES;
	GETTING_FRAMES, // IF I'M ON THE 1-12 FRAME THIS SHOULD BE THE STATE. WHEN ENTERING WITH FRAME NUMBER 13, AT THE END WE CHANGE THE STATE TO FINISHED; WE ALSO TRY TO SEND ALL THE DATA VIA ETHERNET
	FINISHED // READY TO BE READ
} caliper_state_t;

typedef struct {
	uint8_t index;
	uint8_t data;
}bit_context_t;

typedef struct {
	uint8_t index;
	uint8_t data;
}frame_context_t;


#define NUMBER_OF_FRAMES 13

typedef struct{
	caliper_state_t caliper_state;
	digimatic_frame_t frames[NUMBER_OF_FRAMES];
	bit_context_t bit;
	frame_context_t frame;
} digimatic_processing_t;



#endif



typedef struct {
    float number;
    bool unit;
}digimatic_measure_t;

#ifndef TESTING
void onRisingEdgeOfReqSignal(caliper_number curr_caliper);

void onRisingEdgeOfClockSignal(caliper_number curr_caliper, void (*onFinishedGettingFramesFor)(caliper_number));

int getCaliperNumberGivenClockPin(uint16_t CLK_Pin);

digimatic_frame_t* digimaticGetMeasureFrames(caliper_number curr_caliper);

#endif

digimatic_measure_t digimaticMeasure(digimatic_frame_t* digimaticFrame);

bool validCaliperMeasure(digimatic_frame_t* digimaticFrame);

#endif /* INC_DIGIMATIC_H_ */
