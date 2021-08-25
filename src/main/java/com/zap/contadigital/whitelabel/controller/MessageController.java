package com.zap.contadigital.whitelabel.controller;

import com.zap.contadigital.whitelabel.dto.message.MessageRequestDto;
import com.zap.contadigital.whitelabel.dto.message.MessageResponseDto;
import com.zap.contadigital.whitelabel.model.Message;
import com.zap.contadigital.whitelabel.repository.MessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Api(tags = "Message")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation(value = "List All Messages", nickname = "listAll")
    @GetMapping
    public Page<MessageResponseDto> listAll(@PageableDefault(sort = "id",
            direction = Sort.Direction.ASC, page = 0, size = 10)Pageable page){
        Page<Message> messages = messageRepository.findAll(page);
        return MessageResponseDto.convert(messages);
    }

    @ApiOperation(value = "Search Message by Id", nickname = "searchId")
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponseDto> searchId(@PathVariable Long id){
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            return ResponseEntity.ok(new MessageResponseDto(message.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Create new Message", nickname = "createMessage")
    @PostMapping()
    public ResponseEntity<MessageResponseDto> createMessage(@RequestBody @Valid MessageRequestDto messageRequestDto){
        Message message = messageRequestDto.convertToMessage();
        messageRepository.save(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponseDto(message));
    }

}
