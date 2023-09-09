//
// Created by joa-m on 11/1/2021.
//
#include "../../../Comeca/Managers/Inc/analogInManager.h"



uint8_t pTxData[1]= {ADC_CH0_MASK};
uint8_t pRxData[2]= {0, 0};
uint8_t ADC_CH[4] = {ADC_CH0_MASK, ADC_CH1_MASK, ADC_CH2_MASK, ADC_CH3_MASK};

#ifdef TESTING
    // these functions should come from analogInManager.h
    uint16_t readAdc(int inputNum){
        return getAdcMeasure();
    }
#else
    extern SPI_HandleTypeDef hspi3;
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
#endif

bool analogValidate(float analogData){
    return analogData <= MAX_VALID_VALUE && analogData >= MIN_VALID_VALUE;
}




void analogInManager(message_t json){
    int inputNum = getInputNumber(json);

#ifdef TESTING
    uint16_t analog_data = readAdc(inputNum);
#else
    uint16_t analog_data = sendADCReadRequest(inputNum);
#endif
    float receivedData = (analog_data/4096.0)*(3300.0/150.0);

    bool isValid = analogValidate(receivedData);

    if(isValid){
    	sendAnalogInMessage(inputNum, receivedData);
    } else {
    	sendAnalogInWarning(inputNum);
    }
}
