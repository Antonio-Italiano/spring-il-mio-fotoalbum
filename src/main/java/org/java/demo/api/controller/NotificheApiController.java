package org.java.demo.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.java.demo.api.dto.NotificheResponseDto;
import org.java.demo.pojo.Foto;
import org.java.demo.pojo.Notifiche;
import org.java.demo.service.FotoService;
import org.java.demo.service.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class NotificheApiController {

		@Autowired
		NotificheService notificheService;
		
		@Autowired
		FotoService fotoService;
		
		@PostMapping("/{id}/notifiche")
		public ResponseEntity<NotificheResponseDto> store(
				@RequestBody Notifiche notifiche, @Valid BindingResult bindingResult, @PathVariable("id") int id) {
			
			if (bindingResult.hasErrors()) {
				
				return new ResponseEntity<>(
						new NotificheResponseDto(bindingResult), 
						HttpStatus.BAD_REQUEST
					);
			}
			
			Foto foto = fotoService.findById(id).get();
			
			notifiche.setFoto(foto);
			
			notifiche = notificheService.save(notifiche);
			
			return new ResponseEntity<>(
					new NotificheResponseDto(notifiche), 
					HttpStatus.OK);	
		}
		
		@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<NotificheResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
		    
		    List<String> errors = ex.getConstraintViolations()
		            .stream()
		            .map(ConstraintViolation::getMessage)
		            .collect(Collectors.toList());

		   
		    return new ResponseEntity<>(
		            new NotificheResponseDto(errors),
		            HttpStatus.BAD_REQUEST
		    );
		}
		
	}

