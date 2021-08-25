package com.zap.contadigital.whitelabel.dto.message;

import com.zap.contadigital.whitelabel.model.Message;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("Message Request DTO")
public class MessageRequestDto {

    @ApiModelProperty(value = "DynamicMessage")
    @NotBlank(message = "DynamicMessage")
    private String dynamicMessage;


    public Message convertToMessage() {
        return new Message(dynamicMessage);
    }
}
