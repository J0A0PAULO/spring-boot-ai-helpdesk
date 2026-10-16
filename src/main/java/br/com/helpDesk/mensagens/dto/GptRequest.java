package br.com.helpDesk.mensagens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GptRequest(

        @JsonProperty("model")
        String model,

        @JsonProperty("messages")
        List<Messages> messages
) {

    public record Messages(
            @JsonProperty("role")
            String role,

            @JsonProperty("content")
            String content
    ) {


    }

}
