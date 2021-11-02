/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
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
/* Includes ------------------------------------------------------------------*/
#include "main.h"
#include "lwip.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "udpClientRAW.h"
#include "digimatic.h"
#include "../managers/Inc/caliperManager.h"
#include "../managers/Inc/pieceCountManager.h"

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

typedef enum {
    READ_ANALOG_INPUT,
    SET_DIGITAL_OUTPUT,
	INVALID_RX_FRAME
} received_frame_t;

typedef enum {
    CALIPER_MEASURE,
    ANALOG_IN_MEASURE,
    INCREMENT_COUNTER,
    WARNING_NOT_VALID_CALIPER_MEASURE,
    RETRY_ANALOG_IN_MEASURE
} send_frame_t;


/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */

/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/
TIM_HandleTypeDef htim14;

/* USER CODE BEGIN PV */

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
static void MX_TIM14_Init(void);
/* USER CODE BEGIN PFP */

/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */

  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */


  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_TIM14_Init();
  MX_LWIP_Init();
  /* USER CODE BEGIN 2 */
  udpClient_connect();
  HAL_TIM_Base_Start_IT(&htim14);

  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1)
  {
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
	  MX_LWIP_Process();

  }
  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Configure the main internal regulator output voltage
  */
  __HAL_RCC_PWR_CLK_ENABLE();
  __HAL_PWR_VOLTAGESCALING_CONFIG(PWR_REGULATOR_VOLTAGE_SCALE1);
  /** Initializes the RCC Oscillators according to the specified parameters
  * in the RCC_OscInitTypeDef structure.
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSE;
  RCC_OscInitStruct.HSEState = RCC_HSE_ON;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_ON;
  RCC_OscInitStruct.PLL.PLLSource = RCC_PLLSOURCE_HSE;
  RCC_OscInitStruct.PLL.PLLM = 12;
  RCC_OscInitStruct.PLL.PLLN = 96;
  RCC_OscInitStruct.PLL.PLLP = RCC_PLLP_DIV4;
  RCC_OscInitStruct.PLL.PLLQ = 4;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the CPU, AHB and APB buses clocks
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_PLLCLK;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV2;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV2;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_1) != HAL_OK)
  {
    Error_Handler();
  }
  HAL_RCC_MCOConfig(RCC_MCO1, RCC_MCO1SOURCE_PLLCLK, RCC_MCODIV_1);
}

/**
  * @brief TIM14 Initialization Function
  * @param None
  * @retval None
  */
static void MX_TIM14_Init(void)
{

  /* USER CODE BEGIN TIM14_Init 0 */

  /* USER CODE END TIM14_Init 0 */

  /* USER CODE BEGIN TIM14_Init 1 */

  /* USER CODE END TIM14_Init 1 */
  htim14.Instance = TIM14;
  htim14.Init.Prescaler = 800 - 1;
  htim14.Init.CounterMode = TIM_COUNTERMODE_UP;
  htim14.Init.Period = 9375 - 1;
  htim14.Init.ClockDivision = TIM_CLOCKDIVISION_DIV1;
  htim14.Init.AutoReloadPreload = TIM_AUTORELOAD_PRELOAD_DISABLE;
  if (HAL_TIM_Base_Init(&htim14) != HAL_OK)
  {
    Error_Handler();
  }
  /* USER CODE BEGIN TIM14_Init 2 */

  /* USER CODE END TIM14_Init 2 */

}

/**
  * @brief GPIO Initialization Function
  * @param None
  * @retval None
  */
static void MX_GPIO_Init(void)
{
  GPIO_InitTypeDef GPIO_InitStruct = {0};

  /* GPIO Ports Clock Enable */
  __HAL_RCC_GPIOE_CLK_ENABLE();
  __HAL_RCC_GPIOH_CLK_ENABLE();
  __HAL_RCC_GPIOC_CLK_ENABLE();
  __HAL_RCC_GPIOA_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();
  __HAL_RCC_GPIOD_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOE, CAL3_REQ_Pin|CAL2_REQ_Pin|CAL1_REQ_Pin|CAL4_REQ_Pin, GPIO_PIN_RESET);

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOD, DIG_OUT_4_Pin|DIG_OUT_3_Pin, GPIO_PIN_RESET);

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOC, DIG_OUT_2_Pin|DIG_OUT_1_Pin, GPIO_PIN_RESET);

  /*Configure GPIO pins : CAL4_CLK_Pin CAL2_CLK_Pin CAL1_CLK_Pin */
  GPIO_InitStruct.Pin = CAL4_CLK_Pin|CAL2_CLK_Pin|CAL1_CLK_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_RISING;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(GPIOE, &GPIO_InitStruct);

  /*Configure GPIO pins : CAL4_DATA_Pin CAL1_DATA_Pin */
  GPIO_InitStruct.Pin = CAL4_DATA_Pin|CAL1_DATA_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(GPIOE, &GPIO_InitStruct);

  /*Configure GPIO pins : CAL3_REQ_Pin CAL2_REQ_Pin CAL1_REQ_Pin CAL4_REQ_Pin */
  GPIO_InitStruct.Pin = CAL3_REQ_Pin|CAL2_REQ_Pin|CAL1_REQ_Pin|CAL4_REQ_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_OD;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOE, &GPIO_InitStruct);

  /*Configure GPIO pin : CAL3_CLK_Pin */
  GPIO_InitStruct.Pin = CAL3_CLK_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_RISING;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(CAL3_CLK_GPIO_Port, &GPIO_InitStruct);

  /*Configure GPIO pin : CAL3_DATA_Pin */
  GPIO_InitStruct.Pin = CAL3_DATA_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(CAL3_DATA_GPIO_Port, &GPIO_InitStruct);

  /*Configure GPIO pin : CAL2_DATA_Pin */
  GPIO_InitStruct.Pin = CAL2_DATA_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_PULLUP;
  HAL_GPIO_Init(CAL2_DATA_GPIO_Port, &GPIO_InitStruct);

  /*Configure GPIO pins : DIG_OUT_4_Pin DIG_OUT_3_Pin */
  GPIO_InitStruct.Pin = DIG_OUT_4_Pin|DIG_OUT_3_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOD, &GPIO_InitStruct);

  /*Configure GPIO pins : DIG_OUT_2_Pin DIG_OUT_1_Pin */
  GPIO_InitStruct.Pin = DIG_OUT_2_Pin|DIG_OUT_1_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOC, &GPIO_InitStruct);

  /*Configure GPIO pin : PA8 */
  GPIO_InitStruct.Pin = GPIO_PIN_8;
  GPIO_InitStruct.Mode = GPIO_MODE_AF_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_VERY_HIGH;
  GPIO_InitStruct.Alternate = GPIO_AF0_MCO;
  HAL_GPIO_Init(GPIOA, &GPIO_InitStruct);

  /*Configure GPIO pins : uC_PLC_Pin uC_PEDAL_Pin */
  GPIO_InitStruct.Pin = uC_PLC_Pin|uC_PEDAL_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_RISING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOB, &GPIO_InitStruct);

  /* EXTI interrupt init*/
  HAL_NVIC_SetPriority(EXTI2_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI2_IRQn);

  HAL_NVIC_SetPriority(EXTI3_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI3_IRQn);

  HAL_NVIC_SetPriority(EXTI9_5_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI9_5_IRQn);

  HAL_NVIC_SetPriority(EXTI15_10_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI15_10_IRQn);

}

/* USER CODE BEGIN 4 */



void HAL_TIM_PeriodElapsedCallback(TIM_HandleTypeDef *htim){
	if(htim  == &htim14){
		// todo: esta funcion va a cambiar cuando hagamos las pruebas finales, porque es solo un ejemplo.
//		HAL_GPIO_WritePin(CAL1_REQ_GPIO_Port, CAL1_REQ_Pin); // periodicamente tenemos un request, en teoria setteado cada 93.75ms, empieza bajo
		HAL_GPIO_WritePin(CAL1_REQ_GPIO_Port, CAL1_REQ_Pin, GPIO_PIN_RESET); // turn off REQ
		HAL_GPIO_WritePin(CAL2_REQ_GPIO_Port, CAL2_REQ_Pin, GPIO_PIN_RESET); //
		HAL_GPIO_WritePin(CAL3_REQ_GPIO_Port, CAL3_REQ_Pin, GPIO_PIN_RESET); //
		HAL_GPIO_WritePin(CAL4_REQ_GPIO_Port, CAL4_REQ_Pin, GPIO_PIN_RESET); //

		onRisingEdgeOfReqSignal(CALIPER_1); // prendo el flag de poder empezar a leer los bits
		onRisingEdgeOfReqSignal(CALIPER_2);
		onRisingEdgeOfReqSignal(CALIPER_3);
		onRisingEdgeOfReqSignal(CALIPER_4);
	}
}

void HAL_GPIO_EXTI_Callback(uint16_t GPIO_Pin){
	// llega una interrupcion por GPIO

	if(GPIO_Pin ==CAL1_CLK_Pin || GPIO_Pin == CAL2_CLK_Pin || GPIO_Pin == CAL3_CLK_Pin || GPIO_Pin == CAL4_CLK_Pin ){
		//esto se hace si el GPIO_Pin es alguno de los del calibre (CLK)

		onRisingEdgeOfClockSignal(getCaliperNumberGivenClockPin(GPIO_Pin), caliperManager); // aca se realiza la lectura de los bits y se va guardando en el buffer
	}

	if(GPIO_Pin == uC_PEDAL_Pin){
		// que pasa con el pedal de los calibres
	  HAL_TIM_Base_Start_IT(&htim14); // start timer
	  HAL_GPIO_WritePin(CAL1_REQ_GPIO_Port, CAL1_REQ_Pin, GPIO_PIN_SET); // supose REQ starts down, we turn on the REQ
	  HAL_GPIO_WritePin(CAL2_REQ_GPIO_Port, CAL2_REQ_Pin, GPIO_PIN_SET); // supose REQ starts down, we turn on the REQ
	  HAL_GPIO_WritePin(CAL3_REQ_GPIO_Port, CAL3_REQ_Pin, GPIO_PIN_SET); // supose REQ starts down, we turn on the REQ
	  HAL_GPIO_WritePin(CAL4_REQ_GPIO_Port, CAL4_REQ_Pin, GPIO_PIN_SET); // supose REQ starts down, we turn on the REQ
	}

	if(GPIO_Pin == uC_PLC_Pin){
		// que pasa si viene el PLC
		pieceCountManager();
	}
}








//void irqHandler(){
//	// cuando llega una interrupcion
//	AnalogInDigitalOutManager(void);
//}

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */
  __disable_irq();
  while (1)
  {
  }
  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     ex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
