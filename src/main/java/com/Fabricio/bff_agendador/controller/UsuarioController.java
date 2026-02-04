package com.Fabricio.bff_agendador.controller;


import com.Fabricio.bff_agendador.business.UsuarioService;
import com.Fabricio.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.Fabricio.bff_agendador.business.dto.in.LoginRequest;
import com.Fabricio.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.Fabricio.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.Fabricio.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.Fabricio.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.Fabricio.bff_agendador.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "cadastro e login de usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar usuario", description = "Cria um novo Usuario")
    @ApiResponse(responseCode = "200", description = "usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios", description = "Login Usuario")
    @ApiResponse(responseCode = "200", description = "usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequest loginRequest){
        return usuarioService.loginUsuario(loginRequest);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuario por email", description = "buscar dados de usuarios")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuario por id", description = "Deleta usuario")
    @ApiResponse(responseCode = "200", description = "usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de usuario", description = "Atualiza dados de Usuario")
    @ApiResponse(responseCode = "200", description = "usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario nao cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));

    }

    @PutMapping("/endereco")
    @Operation(summary = "atualiza endereco de usuarios", description = "atualiza endereco de Usuario")
    @ApiResponse(responseCode = "200", description = "endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id,dto,token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "atualiza telefone de usuarios", description = "atualiza telefone de Usuario")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id,dto,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "salva endereco de usuarios", description = "salva endereco de usuarios")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastroEndereco(token,dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "salva telefone de usuarios", description = "salva telefone de usuarios")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token,dto));
    }


}
