package com.zap.contadigital.whitelabel.service;

import com.zap.contadigital.whitelabel.dto.client.ClientRequestDto;
import com.zap.contadigital.whitelabel.dto.client.ClientResponseDto;
import com.zap.contadigital.whitelabel.exception.InvalidClientNameException;
import com.zap.contadigital.whitelabel.exception.InvalidCpfNumberException;
import com.zap.contadigital.whitelabel.exception.InvalidPhoneNumberException;
import com.zap.contadigital.whitelabel.model.Client;
import com.zap.contadigital.whitelabel.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Async
    public Future<Page<ClientResponseDto>> listAllClients(String name, Pageable page)
    {
        System.out.println("Called 3rd party to get client info: " + Thread.currentThread().getName());
            if(name == null)
            {
                Page<Client> clients = clientRepository.findAll(page);
                return new AsyncResult<>(ClientResponseDto.convert(clients));
            }
            else
            {
                Page<Client> clients = clientRepository.findByName(name, page);
                return new AsyncResult<>(ClientResponseDto.convert(clients));
            }
    }

    @Async
    public Future<ResponseEntity<ClientResponseDto>> searchClientById(Long id)
    {
        Optional<Client> client = clientRepository.findById(id);
        System.out.println("Called 3rd party to get client info: " + Thread.currentThread().getName());
        if(client.isPresent()){
            return new AsyncResult<>(ResponseEntity.ok(new ClientResponseDto(client.get())));
        }
        return new AsyncResult<>(ResponseEntity.notFound().build());
    }

    private void validateNewClient(ClientRequestDto clientRequestDto)
    {
        if(clientRequestDto.getPhone().length() != 11)
        {
            throw new InvalidPhoneNumberException();
        }
        else if(clientRequestDto.getCpf().length() != 11)
        {
            throw new InvalidCpfNumberException();
        }
        else if(clientRequestDto.getName().length() < 3 || clientRequestDto.getName().length() > 255)
        {
            throw new InvalidClientNameException();
        }
    }

    public ResponseEntity<ClientResponseDto> registerNewClient(ClientRequestDto clientRequestDto)
    {
        try {
            validateNewClient(clientRequestDto);
            Client client = clientRequestDto.convertToClient();
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ClientResponseDto(client));
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Async
    public Future<ResponseEntity<ClientResponseDto>> updateClientInfo(Long id, ClientRequestDto clientRequestDto)
    {
        try {
            validateNewClient(clientRequestDto);
            Optional<Client> oldClient = clientRepository.findById(id);
            if(oldClient.isPresent()){
                Client client = oldClient.get();
                client.setName(clientRequestDto.getName());
                client.setCpf(clientRequestDto.getCpf());
                client.setPhone(clientRequestDto.getPhone());
                clientRepository.save(client);
                return new AsyncResult<>(ResponseEntity.ok(new ClientResponseDto(client)));
            }
        } catch(RuntimeException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Async
    public Future<ResponseEntity<Object>> deleteClientInfo(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()){
            clientRepository.delete(client.get());
            return new AsyncResult<>(ResponseEntity.ok().build());
        }
        else
            return new AsyncResult<>(ResponseEntity.notFound().build());
    }
}
