package org.java.demo.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.java.demo.pojo.Foto;
import org.java.demo.pojo.Notifiche;
import org.java.demo.service.FotoService;
import org.java.demo.service.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class FotoApiController {

	@Autowired
	private FotoService fotoService;
	
	/*
    @Autowired
	private NotificheService notificheService;
	*/
	
	@GetMapping("/foto")
	public ResponseEntity<List<Foto>> indexFoto() {
		
		List<Foto> foto = fotoService.findByVisibileEquals(true);
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@GetMapping("/foto/{id}")
	public ResponseEntity<Foto> showFoto(@PathVariable int id) {
		
		Foto foto = fotoService.findById(id).get();
		return new ResponseEntity<>(foto, HttpStatus.OK);
	}
	
	@PostMapping("/foto/filtro")
	public ResponseEntity<List<Foto>> filtroFoto(@RequestParam(required = false) String titolo){
		
		List<Foto> foto = fotoService.findByTitoloAndVisibileEquals(titolo, true);
		return new ResponseEntity<>(foto,HttpStatus.OK);
	}
	
	/*@PostMapping("/foto")
	public ResponseEntity<FotoResponseDto> storeFoto(
			@RequestBody Foto foto, 
			@Valid BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(
					new FotoResponseDto(bindingResult), 
					HttpStatus.BAD_REQUEST
				);
		}
		
		foto = fotoService.save(foto);
		return new ResponseEntity<>(
				new FotoResponseDto(foto), 
				HttpStatus.OK);	
	}*/
	
	/*@PutMapping("/foto")
	public ResponseEntity<FotoResponseDto> updateFoto(
			@RequestBody Foto foto,
			@Valid BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {	
			return new ResponseEntity<>(
					new FotoResponseDto(bindingResult), 
					HttpStatus.BAD_REQUEST
				);
		}
		
		foto = fotoService.save(foto);
		return new ResponseEntity<>(
				new FotoResponseDto(foto), 
				HttpStatus.OK);	
	}
	
	@DeleteMapping("/foto/{id}")
	public ResponseEntity<FotoResponseDto> deleteFoto(
			@PathVariable int id
		) {
		Optional<Foto> optPizza = fotoService.findById(id);
		if (optPizza.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);	
		}
		
		Foto foto = optPizza.get();
		List<Notifiche> notifiche = foto.getNotifiche();
		for(Notifiche n : notifiche) {	
			notificheService.deleteNotifiche(n);
		}
		
		fotoService.deleteFoto(foto);		
		return new ResponseEntity<>(HttpStatus.OK);	
	}*/
}
