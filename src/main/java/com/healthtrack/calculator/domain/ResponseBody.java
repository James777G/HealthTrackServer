package com.healthtrack.calculator.domain;

import com.healthtrack.calculator.annotation.Warning;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Warning(Warning.Type.ADDITIONAL_FEATURE)
public class ResponseBody<T> {
    private Boolean flag; // Status of the operation: true for success
    private String message; // To provide more information or context about the login result
    private String token; // JWT

    private T t;
}
