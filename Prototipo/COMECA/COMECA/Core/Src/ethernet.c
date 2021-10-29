/*
 * ethernet.c
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */
#include "ethernet.h"
#include "udpClientRAW.h"
#include "string.h"


void caliperManager(char* data){
	return;
}


char rx_frame_type[FRAME_CHARS+1];
char rx_data[DATA_CHARS+1];

char tx_frame[FRAME_CHARS+DELIMITER_CHARS+DATA_CHARS+1];

void destructureFrame(message_t message){
	// assume a message comes in a format like:
	// FRAME_TYPE(10chars), DELIMITER (1 char), DATA (10 chars)

	memcpy(rx_frame_type, message.msg, FRAME_CHARS);
	rx_frame_type[FRAME_CHARS+1] = '\0';
	memcpy(rx_data, message.msg+(FRAME_CHARS+DELIMITER_CHARS), DATA_CHARS);
	rx_data[DATA_CHARS+1] = '\0';
}


void ETHonMessageReceived(message_t message){

	destructureFrame(message);

	if(memcmp(rx_frame_type, "ANALOG_IN", FRAME_CHARS)){
		AnalogInManager();
	}else if(strcmp(rx_frame_type, "DIGITAL_OUT")){
		DigitalOutManager();
	}

	return;
}

void ETHsendMessage(message_t message){
	udpClient_custom_string(message);
}


message_t buildFrame(const char* frame_type, const char* data){
	memset(tx_frame, (int)('_'), FRAME_CHARS+DELIMITER_CHARS+DATA_CHARS+1);
	memcpy(tx_frame, frame_type, FRAME_CHARS);
	tx_frame[FRAME_CHARS] = DELIMITER;
	memcpy(tx_frame, data, DATA_CHARS);
	tx_frame[FRAME_CHARS+DELIMITER_CHARS+DATA_CHARS] = '\0';

	message_t message;
	message.msg = tx_frame;
	message.len = FRAME_CHARS+DELIMITER_CHARS+DATA_CHARS+1;

	return message;
}



int readAdc(void){
	return 0;
}

bool analogValidate(uint32_t analogData){
	return false;
}

void AnalogInManager(void){
	int receivedData = readAdc();
	char data[DATA_CHARS];
    if(analogValidate(receivedData)){ // si es valido
	    itoa(receivedData, data, DATA_CHARS);
		ETHsendMessage(buildFrame("ANALOG_IN_MEASURE", data));

    }else{
		ETHsendMessage(buildFrame("RETRY_ANALOG_IN", data));
    }
}

void DigitalOutManager(void){
	// its either ON/OFF and then comes the OUTPUT Number
	// Format: ON (SPACE) outputNumber
	// e.g. : "ON 3"
//	uint8_t outputNumber = (int) (rx_data[3]-'0');
	if(memcmp(rx_data,"ON", 2)){
//		HAL_GPIO_WritePin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin, GPIO_PIN_SET);
	} else {
//		HAL_GPIO_WritePin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin, GPIO_PIN_RESET);
	}
}

//void AnalogInDigitalOutManager(const char* rx_string){
//
//	received_frame_t received_state = get_state_from_recevied_string();
//    if(received_state == READ_ANALOG_INPUT){
//    		uint16_t receivedData = readAdc();
//
//    }else if(received_state == SET_DIGITAL_OUTPUT){
//        HAL_GPIO_WritePin(DIG_OUT_1((bool)receivedData));
//    }
//}
