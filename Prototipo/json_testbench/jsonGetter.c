//
// Created by joa-m on 11/1/2021.
//

#include "jsonGetter.h"

const char * getFrameType(struct json_value_s* root, message_t json){
//    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    const char * frameType = ((struct json_string_s*)object->start->value->payload)->string;
    return frameType;
}

int getInputNumber(struct json_value_s* root, message_t json){
//    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    int inputNum = atoi(json_value_as_number(object->start->next->value)->number);
    return inputNum;
}

int getOutputNum(struct json_value_s* root, message_t json){
//    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* frameType = object->start;
    int outputNum = atoi(json_value_as_number(frameType->next->value)->number);
    return outputNum;
}
const char * getOutputState(struct json_value_s* root, message_t json){
//    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* frameType = object->start;
    const char * outputState = json_value_as_string(frameType->next->next->value)->string;
    return outputState;
}