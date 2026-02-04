package com.Fabricio.bff_agendador.business;


import com.Fabricio.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.Fabricio.bff_agendador.business.dto.in.LoginRequest;
import com.Fabricio.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.Fabricio.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.Fabricio.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.Fabricio.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.Fabricio.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.Fabricio.bff_agendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return client.salvaUsuario(usuarioDTO);

    }

    public String loginUsuario(LoginRequest loginRequest){
        return client.login(loginRequest);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletaUsuarioPorEmail(String email, String token){
        client.deletaUsuarioPorEmail(email,token);

    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token , UsuarioDTORequest dto){
        return client.atualizaDadoUsuario(dto, token);

    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){

       return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest dto, String token){
        return client.atualizaTelefone(dto,idTelefone,token);

    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto){
        return  client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto){
        return client.cadastraTelefone(dto, token);
    }


}
