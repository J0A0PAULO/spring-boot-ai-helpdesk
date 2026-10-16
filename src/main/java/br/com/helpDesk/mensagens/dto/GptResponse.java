package br.com.helpDesk.mensagens.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GPT(

        @JsonProperty("model")
        String model,

        @JsonProperty("choices")
        List<Choices> choices

) {

    public record Choices(

            @JsonProperty("message")
            Message message
    ) {
        public record Message(

                @JsonProperty("role")
                String role,

                @JsonProperty("content")
                String content
        ) {

        }
    }

}
