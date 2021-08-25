package com.zap.contadigital.whitelabel.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "message")
@NoArgsConstructor
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String fixedMessage;

    @Column
    private String dynamicMessage;

    public Message(String dynamicMessage)
    {
        this.dynamicMessage = dynamicMessage;
        this.fixedMessage = "Essa mensagem é fixa!";
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, fixedMessage, dynamicMessage);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
        {
            return true;
        }
        if(!(obj instanceof Message)){
            return false;
        }
        Message other = (Message) obj;
        return Objects.equals(id, other.id) && Objects.equals(fixedMessage, other.fixedMessage)
                                            && Objects.equals(dynamicMessage, other.dynamicMessage);
    }

}
