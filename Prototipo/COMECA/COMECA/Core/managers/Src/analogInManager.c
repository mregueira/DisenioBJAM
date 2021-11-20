//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/analogInManager.h"

extern SPI_HandleTypeDef hspi3;

#ifdef TESTING
    // these functions should come from analogInManager.h
    int readAdc(int inputNum){
        return getAdcMeasure();
    }

#else
    int readAdc(int inputNum){
        return 100;
    }
#endif

bool analogValidate(uint32_t analogData){
    return analogData <= MAX_VALID_VALUE && analogData >= MIN_VALID_VALUE;
}

void sendADCReadRequest(int inputNum){
//	uint8_t pData[1];
	uint8_t pData[1] = {ADC_CH0_MASK};
	uint16_t timeout = 50;
	HAL_GPIO_WritePin(GPIOA, GPIO_PIN_4, GPIO_PIN_RESET);
	HAL_SPI_Transmit(&hspi3, pData, sizeof(pData), timeout);
}

void analogInManager(message_t json){
    int inputNum = getInputNumber(json);
//    int receivedData = readAdc(inputNum);


    sendADCReadRequest(inputNum);

//    char str2send[100];
//    bool isValid = analogValidate(receivedData);
//    message_t msg = sendAnalogInMessage(str2send, inputNum, receivedData, isValid);
//
//    ETHsendMessage(msg);
}
