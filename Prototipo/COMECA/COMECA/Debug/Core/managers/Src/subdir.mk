################################################################################
# Automatically-generated file. Do not edit!
# Toolchain: GNU Tools for STM32 (9-2020-q2-update)
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../Core/managers/Src/analogInDigitalOutManager.c \
../Core/managers/Src/analogInManager.c \
../Core/managers/Src/caliperManager.c \
../Core/managers/Src/digitalOutManager.c \
../Core/managers/Src/pieceCountManager.c 

OBJS += \
./Core/managers/Src/analogInDigitalOutManager.o \
./Core/managers/Src/analogInManager.o \
./Core/managers/Src/caliperManager.o \
./Core/managers/Src/digitalOutManager.o \
./Core/managers/Src/pieceCountManager.o 

C_DEPS += \
./Core/managers/Src/analogInDigitalOutManager.d \
./Core/managers/Src/analogInManager.d \
./Core/managers/Src/caliperManager.d \
./Core/managers/Src/digitalOutManager.d \
./Core/managers/Src/pieceCountManager.d 


# Each subdirectory must supply rules for building sources it contributes
Core/managers/Src/%.o: ../Core/managers/Src/%.c Core/managers/Src/subdir.mk
	arm-none-eabi-gcc "$<" -mcpu=cortex-m4 -std=gnu11 -g3 -DDEBUG -DUSE_HAL_DRIVER -DSTM32F407xx -c -I../Core/Inc -I../Drivers/STM32F4xx_HAL_Driver/Inc -I../Drivers/STM32F4xx_HAL_Driver/Inc/Legacy -I../Drivers/CMSIS/Device/ST/STM32F4xx/Include -I../Drivers/CMSIS/Include -I../LWIP/App -I../LWIP/Target -I../Middlewares/Third_Party/LwIP/src/include -I../Middlewares/Third_Party/LwIP/system -I../Middlewares/Third_Party/LwIP/src/include/netif/ppp -I../Middlewares/Third_Party/LwIP/src/include/lwip -I../Middlewares/Third_Party/LwIP/src/include/lwip/apps -I../Middlewares/Third_Party/LwIP/src/include/lwip/priv -I../Middlewares/Third_Party/LwIP/src/include/lwip/prot -I../Middlewares/Third_Party/LwIP/src/include/netif -I../Middlewares/Third_Party/LwIP/src/include/compat/posix -I../Middlewares/Third_Party/LwIP/src/include/compat/posix/arpa -I../Middlewares/Third_Party/LwIP/src/include/compat/posix/net -I../Middlewares/Third_Party/LwIP/src/include/compat/posix/sys -I../Middlewares/Third_Party/LwIP/src/include/compat/stdc -I../Middlewares/Third_Party/LwIP/system/arch -O0 -ffunction-sections -fdata-sections -Wall -fstack-usage -MMD -MP -MF"$(@:%.o=%.d)" -MT"$@" --specs=nano.specs -mfpu=fpv4-sp-d16 -mfloat-abi=hard -mthumb -o "$@"

