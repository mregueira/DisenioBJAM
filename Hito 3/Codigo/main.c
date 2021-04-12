typedef struct
{
  ETH_TypeDef                *Instance;     /*!< Register base address       */
  
  ETH_InitTypeDef            Init;          /*!< Ethernet Init Configuration */
  
  uint32_t                   LinkStatus;    /*!< Ethernet link status        */
  
  ETH_DMADescTypeDef         *RxDesc;       /*!< Rx descriptor to Get        */
  
  ETH_DMADescTypeDef         *TxDesc;       /*!< Tx descriptor to Set        */
  
  ETH_DMARxFrameInfos        RxFrameInfos;  /*!< last Rx frame infos         */
  
  __IO HAL_ETH_StateTypeDef  State;         /*!< ETH communication state     */
  
  HAL_LockTypeDef            Lock;          /*!< ETH Lock                    */

#if (USE_HAL_ETH_REGISTER_CALLBACKS == 1)

  void    (* TxCpltCallback)     ( struct __ETH_HandleTypeDef * heth);  /*!< ETH Tx Complete Callback   */
  void    (* RxCpltCallback)     ( struct __ETH_HandleTypeDef * heth);  /*!< ETH Rx  Complete Callback   */
  void    (* DMAErrorCallback)   ( struct __ETH_HandleTypeDef * heth);  /*!< DMA Error Callback      */
  void    (* MspInitCallback)    ( struct __ETH_HandleTypeDef * heth);  /*!< ETH Msp Init callback       */
  void    (* MspDeInitCallback)  ( struct __ETH_HandleTypeDef * heth);  /*!< ETH Msp DeInit callback     */

#endif  /* USE_HAL_ETH_REGISTER_CALLBACKS */

} ETH_HandleTypeDef;


HAL_StatusTypeDef HAL_ETH_TransmitFrame(ETH_HandleTypeDef *heth, uint32_t FrameLength);

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
} send_frame_t


void ETHSendFrameHandler(send_frame_t frame_type, uint16_t* data){
    switch(frame_type){
        case CALIPER_MEASURE:
        // statement using HAL_ETH_TransmitFrame(...);
        break;
        case ANALOG_IN_MEASURE:
        // statement using HAL_ETH_TransmitFrame(...);
        break;
        case INCREMENT_COUNTER:
        // statement using HAL_ETH_TransmitFrame(...);
        break;
        case WARNING_NOT_VALID_CALIPER_MEASURE:
        // statement using HAL_ETH_TransmitFrame(...);
        break;
        case RETRY_ANALOG_IN_MEASURE:
        // statement using HAL_ETH_TransmitFrame(...);
        break;
        default:
        break;
    }   
}

received_frame_t ETHReceiveFrameHandler(uint16_t receivedFrame, uint16_t * receivedData){
    //caracteristica del paquete, supongamos b15 = 1 significa analog in 
    *receivedData = receivedFrame & ~(1<<15);
    if(receivedFrame & 1<<15){
        return READ_ANALOG_INPUT;
    }else{
        return SET_DIGITAL_OUTPUT;
    }
}

bool digimaticValidateFrame(uint16_t measure){
    // ...
}

uint16_t digimaticGetMeasure(void){
    // aca viene el manejo del protocolo digimatic
    // Activo señal de request por x ns. 
    // Prendo CLK por 13 ciclos
    // En cada ciclo leo DATA
    uint16_t data = 0;
    
    // aca va el protocolo digimatic 

    return data;
}

// on pos edge del pedal...
void CaliperManager(void){


    uint16_t measure = 0;
    uint8_t retry_times = 3;
    bool is_valid = false;

    while(retry_times > 0 && !is_valid){
        measure = digimaticGetMeasure(); // this is probably blocking
        is_valid = digimaticValidateFrame(measure);
        retry_times--;
    }

    if(is_valid){
        ETHSendFrameHandler(CALIPER_MEASURE, &measure);
    }else{
        ETHSendFrameHandler(WARNING_NOT_VALID_CALIPER_MEASURE, NULL);
    }
}

bool analogValidate(uint16_t receivedData){
    // ... 
}

// on receive ethernet interrupt
void AnalogInDigitalOutManager(void){
    
    uint16_t receivedFrame = HAL_ETH_GetReceivedFrame(...); // not sure, maybe read a register instead
    uint16_t receivedData = 0;
    received_frame_t received_state = ETHReceiveFrameHandler(receivedFrame, &receivedData);

    if(received_state == READ_ANALOG_INPUT){
        if(analogValidate(receivedData){ // si es valido
            ETHSendFrameHandler(ANALOG_IN_MEASURE, &receivedData);
        }else{
            ETHSendFrameHandler(RETRY_ANALOG_IN_MEASURE, NULL);
        }

    }else if(received_state == SET_DIGITAL_OUTPUT){
        HAL_GPIO_WritePin(receivedData);
    }
}

// on PLC signal interrupt
void pieceCounterManager(void){
    ETHSendFrameHandler(INCREMENT_COUNTER, NULL);
}