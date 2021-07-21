/*
 * timeDriver.c
 *
 *  Created on: 1 oct. 2020
 *      Author: Marcelo
 */
#include "timeDriver.h"

_Bool PWM_SetDuty(TIM_HandleTypeDef *htim, uint32_t Channel, uint8_t duty)
{
	if((duty > 100)||(duty < 0))
	{
		return CONFIG_ERR;
	}
	__HAL_TIM_SET_COMPARE(htim, Channel, ((htim->Init.Period)/100)*duty);
	return CONFIG_OK;
}



