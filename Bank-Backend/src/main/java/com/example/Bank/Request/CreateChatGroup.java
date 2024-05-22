package com.example.Bank.Request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) for creating a new chat group.
 * This class encapsulates the necessary information for creating a new chat
 * group.
 * 
 * @author Pedro Pereira
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateChatGroup {
    /**
     * The description of the chat group.
     */
    @NotNull
    @NotBlank
    String description;
    /**
     * The list of IBANs associated with the chat group.
     */
    @NotNull
    List<String> ibans;
}
