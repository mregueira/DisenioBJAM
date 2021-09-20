/*
 * digimatic.c
 *
 *  Created on: Sep 20, 2021
 *      Author: joa-m
 */

#include "digimatic.h"

static digimatic_processing_t digimatic[NUMBER_OF_CALIPERS];

static GPIO_TypeDef * CAL_DATA_PORT_MAPPING[NUMBER_OF_CALIPERS] = {GPIOE, GPIOB, GPIOA, GPIOE};
static uint16_t CAL_DATA_PIN_MAPPING[NUMBER_OF_CALIPERS] = {GPIO_PIN_11, GPIO_PIN_1, GPIO_PIN_0, GPIO_PIN_4};

static uint16_t CAL_CLK_MAPPING[NUMBER_OF_CALIPERS] = {CAL1_CLK_Pin, CAL2_CLK_Pin, CAL3_CLK_Pin, CAL4_CLK_Pin};

int getCaliperNumberGivenClockPin(uint16_t CLK_Pin){
	int i;
	for(i=0; i<NUMBER_OF_CALIPERS; i++) { if(CAL_CLK_MAPPING[i] == CLK_Pin){ break; } }
	return i;
}

bool getCaliperData(caliper_number curr_caliper){
	return HAL_GPIO_ReadPin(CAL_DATA_PORT_MAPPING[curr_caliper],CAL_DATA_PIN_MAPPING[curr_caliper]);
}

void processBit(caliper_number curr_caliper){
	if(digimatic[curr_caliper].bit.index == 0){digimatic[curr_caliper].frame.data = 0;}

	uint8_t read_bit = getCaliperData(curr_caliper);

	digimatic[curr_caliper].frame.data |= read_bit << digimatic[curr_caliper].bit.index;

	digimatic[curr_caliper].bit.index++;
}


void onRisingEdgeOfReqSignal(caliper_number curr_caliper){
	digimatic[curr_caliper].caliper_state = START;
}


void onRisingEdgeOfClockSignal(caliper_number curr_caliper){
	if(digimatic[curr_caliper].caliper_state != IDLE && digimatic[curr_caliper].caliper_state != FINISHED){
		digimatic[curr_caliper].caliper_state = GETTING_FRAMES; // this doesn't change unless its last frame (implemented below)
		if(digimatic[curr_caliper].frame.index == 0){
			memset(&digimatic[curr_caliper].frames, 0, NUMBER_OF_FRAMES*sizeof(digimatic[curr_caliper].frames[0]));
		}

		processBit(curr_caliper);

		if(digimatic[curr_caliper].bit.index == BITS_PER_FRAME){ // tengo un frame guardado en digimatic.frame.data
			digimatic[curr_caliper].frames[digimatic[curr_caliper].frame.index] = digimatic[curr_caliper].frame.data; // lo guardo en el array
			digimatic[curr_caliper].frame.index++; // avanzo en array
			digimatic[curr_caliper].bit.index = 0; // reinicio el index de bit
		}

		if(digimatic[curr_caliper].frame.index == NUMBER_OF_FRAMES){
			digimatic[curr_caliper].frame.index = 0;
			digimatic[curr_caliper].caliper_state = FINISHED;
		}
	}
}

digimatic_frame_t* digimaticGetMeasure(caliper_number curr_caliper){
	if(digimatic[curr_caliper].caliper_state == FINISHED){
		digimatic[curr_caliper].caliper_state = IDLE;
		return &digimatic[curr_caliper].frames[0];
	}else{
		return NULL;
	}
}