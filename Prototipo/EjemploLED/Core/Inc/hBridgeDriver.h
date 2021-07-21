/*
 * hBridgeDriver.h
 *
 *  Created on: 1 oct. 2020
 *      Author: Marcelo
 */

#ifndef HBRIDGEDRIVER_H_
#define HBRIDGEDRIVER_H_

/* Includes ------------------------------------------------------------------*/
#include "stm32f3xx_hal.h"

/* Define Pines y Handles ----------------------------------------------------*/
#define HBRIDGE_PORT GPIOC
#define IN_A GPIO_PIN_1
#define IN_B GPIO_PIN_2

/* Defines -------------------------------------------------------------------*/
#define TURN_A 1
#define TURN_B 2

#define DRV_OK  1
#define DRV_ERR 0

//void hBridge_Config();
/*
 * Funcion: configura las patas a usar
 * Recibe:
 * 		TO-DO
 */
_Bool hBridge_Turning(uint8_t turn);
/*
 * Funcion: configura sentido de giro
 * Recibe:
 * 		turn = TURN_A, TURN_B
 * Devuelve:
 * 		bool = OK, ERR
 */
void hBridge_Stop(void);

_Bool hBridge_Duty(TIM_HandleTypeDef *htim, uint32_t Channel, uint8_t duty);
/*
 * Funcion: configura duty
 * Recibe:
 * 		duty = 0 - 100
 * Devuelve:
 * 		bool = OK, ERR
 */

#endif /* HBRIDGEDRIVER_H_ */
