package com.zap.contadigital.whitelabel.dto.client;

import com.zap.contadigital.whitelabel.model.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel("Client Request Dto")
@Getter
@Setter
public class ClientRequestDto {
    @ApiModelProperty(value = "Name")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @ApiModelProperty(value = "CPF")
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @ApiModelProperty(value = "Mobile Number")
    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;

    public Client convertToClient()
    {
        return new Client(name, cpf, phone);
    }


}
