package com.zap.contadigital.whitelabel.dto.message;


import com.zap.contadigital.whitelabel.model.Message;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@ApiModel("Message Return DTO")
public class MessageResponseDto {
    private Long id;
    private String fixedMessage;
    private String dynamicMessage;

    public MessageResponseDto(Message message){
        this.fixedMessage = message.getFixedMessage();
        this.dynamicMessage = message.getDynamicMessage();
    }

    public static Page<MessageResponseDto> convert(Page<Message> messages){
        return messages.map(MessageResponseDto::new);
    }

}
