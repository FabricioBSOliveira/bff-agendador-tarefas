package com.Fabricio.bff_agendador.infrastructure.client.config;

import com.Fabricio.bff_agendador.infrastructure.exception.BusinessException;
import com.Fabricio.bff_agendador.infrastructure.exception.ConflictException;
import com.Fabricio.bff_agendador.infrastructure.exception.IllegalArgumentException;
import com.Fabricio.bff_agendador.infrastructure.exception.ResourceNotFoundException;
import com.Fabricio.bff_agendador.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    private final String ERROR_PREFIX = "Error: ";

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        switch (response.status()){
            case 409:
                return new ConflictException(ERROR_PREFIX +  mensagemErro);
            case 403:
                return new ResourceNotFoundException(ERROR_PREFIX +  mensagemErro);
            case 401:
                return new UnauthorizedException(ERROR_PREFIX +  mensagemErro);
            case 400:
                return new IllegalArgumentException(ERROR_PREFIX +  mensagemErro);
            default:
                return new BusinessException(ERROR_PREFIX +  mensagemErro);
        }
    }

    private String mensagemErro(Response response){
        try {
            if (Objects.isNull(response.body())){
                return "";
            }
            return  new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8 );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
