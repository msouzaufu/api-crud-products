package br.com.products.adapter.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateProductDTO {

    @NotBlank(message = "erro no preenchimento do campo")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "erro no preenchimento do campo")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "erro no preenchimento do campo")
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 20, fraction = 2)
    @JsonProperty("price")
    private BigDecimal price;

}
