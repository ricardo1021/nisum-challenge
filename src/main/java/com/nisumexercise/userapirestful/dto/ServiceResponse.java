package com.nisumexercise.userapirestful.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nisumexercise.userapirestful.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String mensaje;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private UserResponse user;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Date created;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Date modified;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private Date lastLogin;
    @JsonInclude(JsonInclude.Include. NON_NULL)
    private boolean isActive;

    public ServiceResponse(String mensaje) {
        this.mensaje = mensaje;
    }
}
