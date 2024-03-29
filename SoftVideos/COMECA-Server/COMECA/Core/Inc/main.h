/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.h
  * @brief          : Header for main.c file.
  *                   This file contains the common defines of the application.
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; Copyright (c) 2021 STMicroelectronics.
  * All rights reserved.</center></h2>
  *
  * This software component is licensed by ST under BSD 3-Clause license,
  * the "License"; You may not use this file except in compliance with the
  * License. You may obtain a copy of the License at:
  *                        opensource.org/licenses/BSD-3-Clause
  *
  ******************************************************************************
  */
/* USER CODE END Header */

/* Define to prevent recursive inclusion -------------------------------------*/
#ifndef __MAIN_H
#define __MAIN_H

#ifdef __cplusplus
extern "C" {
#endif

/* Includes ------------------------------------------------------------------*/
#include "stm32f4xx_hal.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */

/* USER CODE END Includes */

/* Exported types ------------------------------------------------------------*/
/* USER CODE BEGIN ET */


/* USER CODE END ET */

/* Exported constants --------------------------------------------------------*/
/* USER CODE BEGIN EC */

/* USER CODE END EC */

/* Exported macro ------------------------------------------------------------*/
/* USER CODE BEGIN EM */

/* USER CODE END EM */

/* Exported functions prototypes ---------------------------------------------*/
void Error_Handler(void);

/* USER CODE BEGIN EFP */

/* USER CODE END EFP */

/* Private defines -----------------------------------------------------------*/
#define CAL4_CLK_Pin GPIO_PIN_2
#define CAL4_CLK_GPIO_Port GPIOE
#define CAL4_CLK_EXTI_IRQn EXTI2_IRQn
#define CAL4_DATA_Pin GPIO_PIN_4
#define CAL4_DATA_GPIO_Port GPIOE
#define CAL3_REQ_Pin GPIO_PIN_6
#define CAL3_REQ_GPIO_Port GPIOE
#define CAL3_CLK_Pin GPIO_PIN_3
#define CAL3_CLK_GPIO_Port GPIOC
#define CAL3_CLK_EXTI_IRQn EXTI3_IRQn
#define CAL3_DATA_Pin GPIO_PIN_0
#define CAL3_DATA_GPIO_Port GPIOA
#define CAL2_DATA_Pin GPIO_PIN_1
#define CAL2_DATA_GPIO_Port GPIOB
#define CAL2_REQ_Pin GPIO_PIN_7
#define CAL2_REQ_GPIO_Port GPIOE
#define CAL2_CLK_Pin GPIO_PIN_9
#define CAL2_CLK_GPIO_Port GPIOE
#define CAL2_CLK_EXTI_IRQn EXTI9_5_IRQn
#define CAL1_DATA_Pin GPIO_PIN_11
#define CAL1_DATA_GPIO_Port GPIOE
#define CAL1_REQ_Pin GPIO_PIN_13
#define CAL1_REQ_GPIO_Port GPIOE
#define CAL1_CLK_Pin GPIO_PIN_15
#define CAL1_CLK_GPIO_Port GPIOE
#define CAL1_CLK_EXTI_IRQn EXTI15_10_IRQn
#define DIG_OUT_4_Pin GPIO_PIN_13
#define DIG_OUT_4_GPIO_Port GPIOD
#define DIG_OUT_3_Pin GPIO_PIN_15
#define DIG_OUT_3_GPIO_Port GPIOD
#define DIG_OUT_2_Pin GPIO_PIN_7
#define DIG_OUT_2_GPIO_Port GPIOC
#define DIG_OUT_1_Pin GPIO_PIN_9
#define DIG_OUT_1_GPIO_Port GPIOC
#define uC_PLC_Pin GPIO_PIN_6
#define uC_PLC_GPIO_Port GPIOB
#define uC_PLC_EXTI_IRQn EXTI9_5_IRQn
#define uC_PEDAL_Pin GPIO_PIN_8
#define uC_PEDAL_GPIO_Port GPIOB
#define uC_PEDAL_EXTI_IRQn EXTI9_5_IRQn
#define CAL4_REQ_Pin GPIO_PIN_0
#define CAL4_REQ_GPIO_Port GPIOE
/* USER CODE BEGIN Private defines */

/* USER CODE END Private defines */

#ifdef __cplusplus
}
#endif

#endif /* __MAIN_H */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
