package com.zap.contadigital.whitelabel.dto.client;

import com.zap.contadigital.whitelabel.model.Client;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@ApiModel("Client return dto")
@Getter
@Setter
public class ClientResponseDto {
    private Long id;
    private String name;
    private String cpf;
    private String phone;

    public ClientResponseDto(Client client)
    {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.phone = client.getPhone();
    }

    public static Page<ClientResponseDto> convert(Page<Client> clients)
    {
        return clients.map(ClientResponseDto::new);
    }
}
