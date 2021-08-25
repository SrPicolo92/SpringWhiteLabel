package com.zap.contadigital.whitelabel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@Table(name = "client")
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 3, max = 255)
    @NotNull
    private String name;
    @Column
    @Size(min = 11, max = 11)
    @NotNull
    private String cpf;
    @Column
    @Size(min = 11, max = 11)
    @NotNull
    private String phone;

    public Client(String name, String cpf, String phone)
    {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
    }



}
