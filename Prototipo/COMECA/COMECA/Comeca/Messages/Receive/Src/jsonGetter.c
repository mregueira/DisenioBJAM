//
// Created by joa-m on 11/1/2021.
//

#include "../Inc/jsonGetter.h"

// free all! :)

void getFrameType(message_t json, char* frameTypePtr){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    const char * frameType = ((struct json_string_s*)object->start->next->next->next->value->payload)->string;
    strcpy(frameTypePtr, frameType);
    free(root);
}

int getInputNumber(message_t json){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    int inputNum = atoi(json_value_as_number(object->start->next->value)->number);
    free(root);
    return inputNum;
}

int getOutputNum(message_t json){
    int outputNum = (int)((char)(json.msg[57]) -'0');
    if(outputNum > 3){
       outputNum = (int)((char)(json.msg[58]) -'0');
    }
    return outputNum;
}
void getOutputState(message_t json, char* outputStatePtr){
	outputStatePtr[0] = json.msg[38];
	outputStatePtr[1] = json.msg[39];
}
