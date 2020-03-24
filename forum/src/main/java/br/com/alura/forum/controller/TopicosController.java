package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.repository.CursoRepository;
import br.com.alura.forum.controller.repository.TopicoRepository;
import br.com.alura.forum.model.Topico;

@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping("/topicos")
	public List<TopicoDTO> lista(String nomeCurso) {
		
		if(nomeCurso == null) {
			List <Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.convert(topicos);
		} else {
			List <Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDTO.convert(topicos);
		}
		
	}
	
	@PostMapping("/topicos")
	public void cadastrar(@RequestBody TopicoForm topicoForm ) {
		Topico topico = topicoForm.convert(cursoRepository);
		topicoRepository.save(topico);
		
	}
	
}
