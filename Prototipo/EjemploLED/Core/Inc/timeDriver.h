/*
 * timeDriver.h
 *
 *  Created on: 1 oct. 2020
 *      Author: Marcelo
 */

#ifndef TIMEDRIVER_H_
#define TIMEDRIVER_H_

/* Includes ------------------------------------------------------------------*/
#include "stm32f3xx_hal.h"

/* Defines -------------------------------------------------------------------*/
#define CONFIG_OK  1
#define CONFIG_ERR 0

_Bool PWM_SetDuty(TIM_HandleTypeDef *htim, uint32_t Channel, uint8_t duty);
/*
 * Funcion: configura el duty del PWM usado
 * Recibe:
 * 		duty: numero entero entre 0 y 100
 */

#endif /* TIMEDRIVER_H_ */
