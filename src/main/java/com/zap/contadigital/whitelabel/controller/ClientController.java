package com.zap.contadigital.whitelabel.controller;

import com.zap.contadigital.whitelabel.dto.client.ClientRequestDto;
import com.zap.contadigital.whitelabel.dto.client.ClientResponseDto;
import com.zap.contadigital.whitelabel.model.Client;
import com.zap.contadigital.whitelabel.repository.ClientRepository;
import com.zap.contadigital.whitelabel.service.ClientService;
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
import java.util.concurrent.ExecutionException;

@Api(tags = "Client")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @ApiOperation(value = "List All Clients", nickname = "listAll")
    @GetMapping
    public Page<ClientResponseDto> listAll(@RequestParam(required = false) String name,
            @PageableDefault(sort = "name", direction = Sort.Direction.ASC,
            page = 0, size = 10)Pageable page) throws ExecutionException, InterruptedException {
        return clientService.listAllClients(name, page).get();
    }

    @ApiOperation(value = "Search Client by ID", nickname = "searchId")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> searchId(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return clientService.searchClientById(id).get();
    }

    @ApiOperation(value = "Register a new Client", nickname = "newClient")
    @PostMapping()
    public ResponseEntity<ClientResponseDto> newClient(@Valid @RequestBody ClientRequestDto clientRequestDto){
        return clientService.registerNewClient(clientRequestDto);
    }

    @ApiOperation(value = "Update Client", nickname = "updateClient")
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable(value = "id") Long id,
                                                          @Valid @RequestBody ClientRequestDto clientRequestDto)
                                             throws ExecutionException, InterruptedException {
        return clientService.updateClientInfo(id, clientRequestDto).get();
    }

    @ApiOperation(value = "Delete Client", nickname = "deleteClient")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id)
                                    throws ExecutionException, InterruptedException {
        return clientService.deleteClientInfo(id).get();
    }

}
