/*
 * hBridgeDriver.c
 *
 *  Created on: 2 oct. 2020
 *      Author: Marcelo
 */
#include "timeDriver.h"
#include "hBridgeDriver.h"

_Bool hBridge_Turning(uint8_t turn)
{
	if(turn == TURN_A)
	{
		HAL_GPIO_WritePin(HBRIDGE_PORT, IN_A, GPIO_PIN_SET);
		HAL_GPIO_WritePin(HBRIDGE_PORT, IN_B, GPIO_PIN_RESET);
		return DRV_OK;
	}
	else if(turn == TURN_B)
	{
		HAL_GPIO_WritePin(HBRIDGE_PORT, IN_A, GPIO_PIN_RESET);
		HAL_GPIO_WritePin(HBRIDGE_PORT, IN_B, GPIO_PIN_SET);
		return DRV_OK;
	}
	else
	{
		return DRV_ERR;
	}
}

void hBridge_Stop(void)
{
	HAL_GPIO_WritePin(HBRIDGE_PORT, IN_A, GPIO_PIN_RESET);
	HAL_GPIO_WritePin(HBRIDGE_PORT, IN_B, GPIO_PIN_RESET);
}

_Bool hBridge_Duty(TIM_HandleTypeDef *htim, uint32_t Channel, uint8_t duty)
{
	return PWM_SetDuty(htim, Channel, duty);
}
