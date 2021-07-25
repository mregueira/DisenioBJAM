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

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include "stdbool.h"
#include <string.h> // for memset
/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */
typedef enum {
    READ_ANALOG_INPUT,
    SET_DIGITAL_OUTPUT
} received_frame_t;

typedef enum {
    CALIPER_MEASURE,
    ANALOG_IN_MEASURE,
    INCREMENT_COUNTER,
    WARNING_NOT_VALID_CALIPER_MEASURE,
    RETRY_ANALOG_IN_MEASURE
} send_frame_t;

typedef enum{
	START, // SEND REQUEST --> SET THIS STATE; WHEN ENTERING THE CLOCK INTERRUPT FUNCTION, WE GET THE FIRST FRAME, WE SET THE STATE TO GETTING FRAMES;
	GETTING_FRAMES, // IF I'M ON THE 1-12 FRAME THIS SHOULD BE THE STATE. WHEN ENTERING WITH FRAME NUMBER 13, AT THE END WE CHANGE THE STATE TO FINISHED; WE ALSO TRY TO SEND ALL THE DATA VIA ETHERNET
	FINISHED, // READY TO BE READ
	IDLE // ONCE IT'S READ --> IDLE
} caliper_state_t;


typedef struct {
	uint8_t index;
	uint8_t data;
}bit_context_t;

typedef struct {
	uint8_t index;
	uint8_t data;
}frame_context_t;

typedef struct{
	bit_context_t bit;
	frame_context_t frame;
} digimatic_processing_t;


typedef uint8_t digimatic_frame_t; // every frame needs 4 bits.

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
#define CAL_1_DATA GPIOE,GPIO_PIN_11

#define DIG_OUT_1_PORT_AND_PIN GPIOC,GPIO_PIN_9
#define DIG_OUT_1(x) DIG_OUT_1_PORT_AND_PIN,x

#define DIG_OUT_2_PORT_AND_PIN GPIOC,GPIO_PIN_7
#define DIG_OUT_2(x) DIG_OUT_2_PORT_AND_PIN,x

#define DIG_OUT_3_PORT_AND_PIN GPIOD,GPIO_PIN_15
#define DIG_OUT_3(x) DIG_OUT_3_PORT_AND_PIN,x

#define DIG_OUT_4_PORT_AND_PIN GPIOD,GPIO_PIN_13
#define DIG_OUT_4(x) DIG_OUT_4_PORT_AND_PIN,x

#define NUMBER_OF_FRAMES 13
#define BITS_PER_FRAME 4

/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/

/* USER CODE BEGIN PV */
caliper_state_t caliper_state = FINISHED;

digimatic_frame_t digimatic_frames[NUMBER_OF_FRAMES];

digimatic_processing_t digimatic = {.bit={.index=0, .data=0},.frame={.index=0,.data=0}};

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
static void MX_GPIO_Init(void);
/* USER CODE BEGIN PFP */
void testGpioPin(GPIO_TypeDef* GPIO_PORT, uint16_t GPIO_PIN);

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
  /* USER CODE BEGIN 2 */
//  testGpioPin(DIG_OUT_1_PORT_AND_PIN);
  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */
  while (1)
  {
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */
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
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSI;
  RCC_OscInitStruct.HSIState = RCC_HSI_ON;
  RCC_OscInitStruct.HSICalibrationValue = RCC_HSICALIBRATION_DEFAULT;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_ON;
  RCC_OscInitStruct.PLL.PLLSource = RCC_PLLSOURCE_HSI;
  RCC_OscInitStruct.PLL.PLLM = 8;
  RCC_OscInitStruct.PLL.PLLN = 168;
  RCC_OscInitStruct.PLL.PLLP = RCC_PLLP_DIV2;
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
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV4;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV2;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_5) != HAL_OK)
  {
    Error_Handler();
  }
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
  __HAL_RCC_GPIOC_CLK_ENABLE();
  __HAL_RCC_GPIOB_CLK_ENABLE();

  /*Configure GPIO pin Output Level */
  HAL_GPIO_WritePin(GPIOC, GPIO_PIN_9, GPIO_PIN_RESET);

  /*Configure GPIO pin : CAL1_DATA_Pin */
  GPIO_InitStruct.Pin = CAL1_DATA_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_INPUT;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(CAL1_DATA_GPIO_Port, &GPIO_InitStruct);

  /*Configure GPIO pins : CAL1_REQ_Pin CAL1_CLK_Pin */
  GPIO_InitStruct.Pin = CAL1_REQ_Pin|CAL1_CLK_Pin;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_RISING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOE, &GPIO_InitStruct);

  /*Configure GPIO pin : PC9 */
  GPIO_InitStruct.Pin = GPIO_PIN_9;
  GPIO_InitStruct.Mode = GPIO_MODE_OUTPUT_PP;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  GPIO_InitStruct.Speed = GPIO_SPEED_FREQ_LOW;
  HAL_GPIO_Init(GPIOC, &GPIO_InitStruct);

  /*Configure GPIO pin : PB3 */
  GPIO_InitStruct.Pin = GPIO_PIN_3;
  GPIO_InitStruct.Mode = GPIO_MODE_IT_RISING;
  GPIO_InitStruct.Pull = GPIO_NOPULL;
  HAL_GPIO_Init(GPIOB, &GPIO_InitStruct);

  /* EXTI interrupt init*/
  HAL_NVIC_SetPriority(EXTI3_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI3_IRQn);

  HAL_NVIC_SetPriority(EXTI15_10_IRQn, 0, 0);
  HAL_NVIC_EnableIRQ(EXTI15_10_IRQn);

}

/* USER CODE BEGIN 4 */

void HAL_GPIO_EXTI_Callback(uint16_t GPIO_Pin){
	switch(GPIO_Pin){
	case CAL1_CLK_Pin:
		onRisingEdgeOfClockSignal();
		break;
	case CAL1_REQ_Pin:
		onRisingEdgeOfReqSignal();
		break;
	default:
		break;
	}
}


void testGpioPin(GPIO_TypeDef* GPIO_PORT, uint16_t GPIO_PIN){
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_SET);
	HAL_Delay(1000);
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_RESET);
	HAL_Delay(1000);
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_SET);
	HAL_Delay(1000);
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_RESET);
	HAL_Delay(1000);
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_SET);
	HAL_Delay(1000);
	HAL_GPIO_WritePin(GPIO_PORT, GPIO_PIN, GPIO_PIN_RESET);
	HAL_Delay(1000);
}


void processBit(void){
	if(digimatic.bit.index == 0){digimatic.frame.data = 0;}

	uint8_t read_bit = HAL_GPIO_ReadPin(CAL_1_DATA);

	digimatic.frame.data |= read_bit << digimatic.bit.index;

	digimatic.bit.index++;
}


void onRisingEdgeOfReqSignal(void){
	// esta funcion tiene sentido cuando probamos el calibre con analog
	caliper_state = START;
}


void onRisingEdgeOfClockSignal(void){
	if(caliper_state != IDLE && caliper_state != FINISHED){
		caliper_state = GETTING_FRAMES; // this doesn't change unless its last frame (implemented below)
		if(digimatic.frame.index == 0){
			memset(&digimatic_frames, 0, NUMBER_OF_FRAMES*sizeof(digimatic_frames[0]));
		}

		processBit();

		if(digimatic.bit.index == BITS_PER_FRAME){ // tengo un frame guardado en digimatic.frame.data
			digimatic_frames[digimatic.frame.index] = digimatic.frame.data; // lo guardo en el array
			digimatic.frame.index++; // avanzo en array
			digimatic.bit.index = 0; // reinicio el index de bit
		}

		if(digimatic.frame.index == NUMBER_OF_FRAMES){
			digimatic.frame.index = 0;
			caliper_state = FINISHED;
		}
	}
}

digimatic_frame_t* digimaticGetMeasure(void){
	if(caliper_state == FINISHED){
		caliper_state = IDLE;
		return &digimatic_frames[0];
	}else{
		return NULL;
	}
}


bool analogValidate(uint32_t analogData){
	return false;
}

void AnalogInDigitalOutManager(void){
//    uint16_t receivedFrame = HAL_ETH_GetReceivedFrame(...); // not sure, maybe read a register instead
//    received_frame_t received_state = ETHReceiveFrameHandler(receivedFrame, &receivedData);
    uint16_t receivedData = GPIO_PIN_SET;
	received_frame_t received_state = SET_DIGITAL_OUTPUT;

    if(received_state == READ_ANALOG_INPUT){
        if(analogValidate(receivedData)){ // si es valido
//            ETHSendFrameHandler(ANALOG_IN_MEASURE, &receivedData);
        }else{
//            ETHSendFrameHandler(RETRY_ANALOG_IN_MEASURE, NULL);
        }

    }else if(received_state == SET_DIGITAL_OUTPUT){
        HAL_GPIO_WritePin(DIG_OUT_1((bool)receivedData));
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
