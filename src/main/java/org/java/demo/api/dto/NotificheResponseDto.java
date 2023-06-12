package org.java.demo.api.dto;

import java.util.List;

import org.java.demo.pojo.Notifiche;
import org.springframework.validation.BindingResult;

public class NotificheResponseDto {

	Notifiche notifiche;
	BindingResult bindingResult;
	
	public NotificheResponseDto(Notifiche notifiche) {
			
		setNotifiche(notifiche);
		}
	public NotificheResponseDto(BindingResult bindingResult) {
		
		setBindingResult(bindingResult);
	}
	public NotificheResponseDto(Notifiche notifiche, BindingResult bindingResult) {
		
		setNotifiche(notifiche);
		setBindingResult(bindingResult);
	}
	 public Notifiche getNotifiche() {
		return notifiche;
	}
	public void setNotifiche(Notifiche notifiche) {
		this.notifiche = notifiche;
	}
	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	private List<String> errors;

    public NotificheResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }	
}