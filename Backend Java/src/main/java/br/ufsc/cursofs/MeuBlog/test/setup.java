package br.ufsc.cursofs.MeuBlog.test;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.ufsc.cursofs.MeuBlog.entities.Assunto;
import br.ufsc.cursofs.MeuBlog.entities.Comentario;
import br.ufsc.cursofs.MeuBlog.entities.Editor;
import br.ufsc.cursofs.MeuBlog.entities.Postagem;
import br.ufsc.cursofs.MeuBlog.repositories.AssuntoRepository;
import br.ufsc.cursofs.MeuBlog.repositories.ComentarioRepository;
import br.ufsc.cursofs.MeuBlog.repositories.EditorRepository;
import br.ufsc.cursofs.MeuBlog.repositories.PostagemRepository;

@Configuration
@Profile("test")
public class setup implements CommandLineRunner {
	
	// dependency injection
	@Autowired
	private EditorRepository editorRepository;
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private AssuntoRepository assuntoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Editor mateus = new Editor(null, "Mateus Henrique", "M@teus");
		Editor maria = new Editor(null, "Maria Eduarda", "M@ria");
		editorRepository.saveAll(Arrays.asList(mateus, maria));
		
		Postagem post1 = new Postagem
				(
					null,
					Instant.parse("2022-09-28T21:15:07Z"),
					"Arduino",
					"Arduino é uma plataforma de prototipagem eletrônica de hardware livre e de placa única, projetada com um microcontrolador Atmel AVR com suporte de entrada/saída embutido, uma linguagem de programação padrão, a qual tem origem em Wiring, e é essencialmente C/C++.",
					mateus
				);
		
		Postagem post2 = new Postagem
				(
						null,
						Instant.parse("2022-09-28T21:18:07Z"),
						"RaspBerry pi",
						"Raspberry Pi é uma série de mini-computadores de placa única multiplataforma, de tamanho reduzido com componentes integrados, que se conecta a um monitor de computador ou televisão, e usa um teclado e um mouse padrão.",
						mateus
				);
		
		Postagem post3 = new Postagem
				(
					null,
					Instant.parse("2022-09-28T21:24:07Z"),
					"Computador",
					"Computador é um conjunto de componentes eletrônicos (máquina) capaz de executar variados tipos de algoritmos e tratamento de informações (processamento de dados).",
					mateus
				);
		
		Postagem post4 = new Postagem
				(
					null, 
					Instant.parse("2022-09-28T21:28:07Z"), 
					"Cachorro",
					"O cão, no Brasil também chamado de cachorro, é um mamífero carnívoro da família dos canídeos, subespécie do lobo, e talvez o mais antigo animal domesticado pelo ser humano. Teorias postulam que surgiu do lobo cinzento no continente asiático há mais de 100 000 anos.",
					maria
				);
		
		Postagem post5 = new Postagem
				(
					null,
					Instant.parse("2022-09-28T21:32:07Z"),
					"Gato",
					"O gato, também conhecido como gato caseiro, gato urbano ou gato doméstico, é um mamífero carnívoro da família dos felídeos, muito popular como animal de estimação. Ocupando o topo da cadeia alimentar, é predador natural de diversos animais, como roedores, pássaros, lagartixas e alguns insetos.",
					maria
				);
		postagemRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
		
		mateus.addPostagem(post1);
		mateus.addPostagem(post2);
		mateus.addPostagem(post3);
		maria.addPostagem(post4);
		maria.addPostagem(post5);
		editorRepository.saveAll(Arrays.asList(mateus, maria));
		
		Comentario c1 = new Comentario(null, "Antonio", Instant.parse("2022-09-29T21:03:07Z"), "O arduino é muito interessante para fazer automação residencial", post1);
		Comentario c2 = new Comentario(null, "Maria Eduarda", Instant.parse("2022-09-29T21:08:07Z"), "Quero colocar uma iluminação automática com energia solar no corredor da minha casa utilizando o arduino", post1);
		Comentario c3 = new Comentario(null, "Gabriel", Instant.parse("2022-09-29T21:10:07Z"), "Seria legal montar alguns servidores com algum desses", post2);
		Comentario c4 = new Comentario(null, "Mateus", Instant.parse("2022-09-29T21:11:07Z"), "Eu tenho uma cachorra, o nome dela é duquesa", post4);
		Comentario c5 = new Comentario(null, "Jorcelene", Instant.parse("2022-09-29T21:11:07Z"), "Gosto muito dos cães", post4);
		Comentario c6 = new Comentario(null, "Adriana", Instant.parse("2022-09-29T21:12:07Z"), "Eu cuido de dois", post4);
		Comentario c7 = new Comentario(null, "Maria Eduarda", Instant.parse("2022-09-29T21:13:07Z"), "Eu amo meus doguinhos", post4);
		comentarioRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		
		Assunto a1 = new Assunto(null, "Eletrônica");
		Assunto a2 = new Assunto(null, "Automação residencial");

		Assunto a3 = new Assunto(null, "Servidores");

		Assunto a4 = new Assunto(null, "Mini-PC");
		Assunto a5 = new Assunto(null, "Computação");
		
		Assunto a6 = new Assunto(null, "Pets");

		Assunto a7 = new Assunto(null, "Cães");
		Assunto a8 = new Assunto(null, "Gatos");
		assuntoRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8));
		
		post1.addAssunto(a1);
		post1.addAssunto(a2);
		
		post2.addAssunto(a3);
		post2.addAssunto(a4);
		
		post3.addAssunto(a3);
		post3.addAssunto(a5);
		
		post4.addAssunto(a6);
		post4.addAssunto(a7);
		
		post5.addAssunto(a6);
		post5.addAssunto(a8);
		postagemRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
		
	}
}
