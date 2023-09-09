//
// Created by joa-m on 11/1/2021.
//

#include "../Inc/jsonGetter.h"

void getActionType(message_t json_to_parse, char* actionTypePtr){
    cJSON *json = cJSON_Parse((const char *)json_to_parse.msg);
    cJSON *action = cJSON_GetObjectItemCaseSensitive(json, "action");
    strcpy(actionTypePtr, action->valuestring);
    cJSON_Delete(json);
}


int getInputNumber(message_t json_to_parse){
    cJSON *json = cJSON_Parse((const char *)json_to_parse.msg);
    cJSON *data = cJSON_GetObjectItemCaseSensitive(json, "data");
    cJSON *inputNumberObj = cJSON_GetObjectItemCaseSensitive(data, "inputNumber");
    int inputNum = inputNumberObj->valueint;
    cJSON_Delete(json);
    return inputNum;
}

int getOutputNum(message_t json_to_parse){
    cJSON *json = cJSON_Parse((const char *)json_to_parse.msg);
    cJSON *data = cJSON_GetObjectItemCaseSensitive(json, "data");
    cJSON *outputNumberObj = cJSON_GetObjectItemCaseSensitive(data, "outputNumber");
    int outputNum = outputNumberObj->valueint;
    cJSON_Delete(json);
    return outputNum;
}
void getOutputState(message_t json_to_parse, char* outputStatePtr){

    cJSON *json = cJSON_Parse((const char *)json_to_parse.msg);
    cJSON *data = cJSON_GetObjectItemCaseSensitive(json, "data");
    cJSON *outputNumberObj = cJSON_GetObjectItemCaseSensitive(data, "outputState");
    strcpy(outputStatePtr, outputNumberObj->valuestring);
    cJSON_Delete(json);
}