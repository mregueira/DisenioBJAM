/*
 * ethernet.c
 *
 *  Created on: Oct 28, 2021
 *      Author: joa-m
 */
#include "ethernet.h"
#include "udpClientRAW.h"
#include "string.h"
#include "json.h"
#include <stdio.h>

void caliperManager(char* data){
	return;
}


void ETHonMessageReceived(message_t json){

	struct json_value_s* root = json_parse(json.msg, json.len);
	assert(root);
	assert(root->type == json_type_object);

	struct json_object_s* object = (struct json_object_s*)root->payload;
	assert(object);

	struct json_object_element_s* frameType = object->start;

	assert(0 == strcmp(frameType->name->string, "frameType"));
	assert(frameType->value->type == json_type_string);

	struct json_string_s* frameTypeValue = (struct json_string_s*)frameType->value->payload;

	if(strcmp(frameTypeValue->string, "READ_ANALOG_IN") == 0){
		AnalogInManager(json);
	}

	if(strcmp(frameTypeValue->string, "DIGITAL_OUT") == 0){
		DigitalOutManager(json);
	}


	return;
}

void ETHsendMessage(message_t message){
	udpClient_custom_string(message);
}

int readAdc(void){
	return 0;
}

bool analogValidate(uint32_t analogData){
	return false;
}

void AnalogInManager(message_t json){
	int receivedData = readAdc();

    if(analogValidate(receivedData)){ // si es valido

		struct json_value_s* root = json_parse(json.msg, json.len);
		struct json_object_s* object = (struct json_object_s*)root->payload;
		struct json_object_element_s* frameType = object->start;
		struct json_object_element_s* inputNumber = frameType->next->value;
		struct json_number_s* extracted = json_value_as_number(inputNumber);
		int inputNum = atoi(extracted->number);

		char str2send[100];
		int len = sprintf(str2send,"{\"frameType\": \"MEASURED_ANALOG_IN\",\"inputNumber\": %d, \"analogData\": %d}", inputNum, receivedData);
		message_t msg2send;
		msg2send.msg = str2send;
		msg2send.len = len;

		ETHsendMessage(msg2send);


    }else{

		char str2send[100];
		int len = sprintf(str2send,"{\"frameType\": \"RETRY_ANALOG_IN\"}");
		message_t msg2send;
		msg2send.msg = str2send;
		msg2send.len = len;

		ETHsendMessage(msg2send);
    }
}

void DigitalOutManager(message_t json){
//	uint8_t outputNumber = (int) (rx_data[3]-'0');
//	if(memcmp(rx_data,"ON", 2)){
////		HAL_GPIO_WritePin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin, GPIO_PIN_SET);
//	} else {
////		HAL_GPIO_WritePin(GPIO_TypeDef* GPIOx, uint16_t GPIO_Pin, GPIO_PIN_RESET);
//	}
}

