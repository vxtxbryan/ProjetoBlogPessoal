package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Porta padrao = 8080
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Ciclo de vida por classe
public class UsuarioRepositoryTest {

	
	@Autowired //Utilizar metodos da interface repository
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll //Antes de rodar os testes insira os dados no banco
	void start(){ //Iniciar o banco de dados
        
        usuarioRepository.deleteAll(); //Deletar o banco de dados de teste

		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278",
                                           "https://i.imgur.com/FETvs2O.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com.br", "13465278", 
                                           "https://i.imgur.com/NtyGneo.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com.br", "13465278",
                                           "https://i.imgur.com/mB3VM2N.jpg"));

        usuarioRepository.save(new Usuario(0L, "Paulo Antunes Silva", "paulo@email.com.br", "13465278", 
                                           "h ttps://i.imgur.com/JR7kUFU.jpg"));
	}
	
	@Test
	@DisplayName("🧛‍♂️ Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	
	}
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(4, listaDeUsuarios.size()); //Checagem da lista para ver se tem tamanho 3
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva")); // Começa do zero porque é collection
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
}
}
