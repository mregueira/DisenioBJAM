//
// Created by joa-m on 11/1/2021.
//

#include <assert.h>
#include "../Inc/jsonGetter.h"

// free all! :)
struct json_object_element_s* seekField(struct json_object_element_s* field, const char* fieldName){
    while(field->next != NULL && strcmp(field->name->string, fieldName) != 0){
        field = field->next;
    }
    return field;
}

void getActionType(message_t json, char* actionTypePtr){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* field;

    field = seekField(object->start, "action");
    assert(strcmp(field->name->string, "action") == 0);

    struct json_string_s* action_payload = (struct json_string_s*) field->value->payload;
    const char * actionType = action_payload->string;

    strcpy(actionTypePtr, actionType);
    free(root);
}


int getInputNumber(message_t json){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* field;

    field = seekField(object->start, "data");
    assert(strcmp(field->name->string, "data") == 0);

//    struct json_object_s* action_payload = field->value->payload;
//    field = seekField(action_payload->start, "getAnalogIn");
//    assert(strcmp(field->name->string, "getAnalogIn") == 0);

    struct json_object_s* analogInPayload = field->value->payload;
    field = seekField(analogInPayload->start, "inputNumber");
    assert(strcmp(field->name->string, "inputNumber") == 0);

    int inputNum = atoi(json_value_as_number(field->value)->number);
    free(root);
    return inputNum;
}

int getOutputNum(message_t json){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* field;

    field = seekField(object->start, "data");
    assert(strcmp(field->name->string, "data") == 0);

//    struct json_object_s* action_payload = field->value->payload;
//    field = seekField(action_payload->start, "setSalidaDigital");
//    assert(strcmp(field->name->string, "setSalidaDigital") == 0);

    struct json_object_s* analogInPayload = field->value->payload;
    field = seekField(analogInPayload->start, "outputNumber");
    assert(strcmp(field->name->string, "outputNumber") == 0);

    int outputNum = atoi(json_value_as_number(field->value)->number);
    free(root);
    return outputNum;
}
void getOutputState(message_t json, char* outputStatePtr){
    struct json_value_s* root = json_parse(json.msg, json.len);
    struct json_object_s* object = (struct json_object_s*)root->payload;
    struct json_object_element_s* field;

    field = seekField(object->start, "data");
    assert(strcmp(field->name->string, "data") == 0);

//    struct json_object_s* action_payload = field->value->payload;
//    field = seekField(action_payload->start, "setSalidaDigital");
//    assert(strcmp(field->name->string, "setSalidaDigital") == 0);

    struct json_object_s* analogInPayload = field->value->payload;
    field = seekField(analogInPayload->start, "outputState");
    assert(strcmp(field->name->string, "outputState") == 0);

    strcpy(outputStatePtr, json_value_as_string(field->value)->string);
    free(root);
}