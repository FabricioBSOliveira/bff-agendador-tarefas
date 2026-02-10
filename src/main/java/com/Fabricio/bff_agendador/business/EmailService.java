package com.Fabricio.bff_agendador.business;

import com.Fabricio.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.Fabricio.bff_agendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto){
        emailClient.enviarEmail(dto);
    }
}
