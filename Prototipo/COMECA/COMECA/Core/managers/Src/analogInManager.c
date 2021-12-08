//
// Created by joa-m on 11/1/2021.
//
#include "../Inc/analogInManager.h"

extern SPI_HandleTypeDef hspi3;

uint8_t pTxData[1]= {ADC_CH0_MASK};
uint8_t pRxData[2]= {0, 0};

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

uint8_t ADC_CH[4] = {ADC_CH0_MASK, ADC_CH1_MASK, ADC_CH2_MASK, ADC_CH3_MASK};

uint16_t sendADCReadRequest(int inputNum){
	HAL_GPIO_WritePin(GPIOA, GPIO_PIN_4, RESET);
	pTxData[0]=ADC_CH[inputNum];
	uint32_t Timeout = HAL_MAX_DELAY;
	HAL_SPI_Transmit(&hspi3, pTxData, 1, Timeout);
	HAL_SPI_Receive(&hspi3, pRxData, 2, Timeout);
	HAL_GPIO_WritePin(GPIOA, GPIO_PIN_4, SET);
	pRxData[0] = pRxData[0] & 0x7F;
	pRxData[1] = pRxData[1] >> 3;

	return (((uint16_t)pRxData[0]) << 5) | pRxData[1];
}

void analogInManager(message_t json){
    int inputNum = getInputNumber(json);

    uint16_t analog_data = sendADCReadRequest(inputNum);

    float receivedData = (analog_data/4096.0)*(3300.0/150.0);

    char str2send[100];
    bool isValid = analogValidate(receivedData);
    message_t msg = sendAnalogInMessage(str2send, inputNum, receivedData, isValid);

    ETHsendMessage(msg);
}