package org.java.demo.service;

import java.util.List;
import java.util.Optional;
import org.java.demo.pojo.Notifiche;
import org.java.demo.repo.NotificheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificheService {

	@Autowired
	private NotificheRepo notificheRepo;
	
	public List<Notifiche> findAll(){	
		return notificheRepo.findAll();
	}
	
	public Optional<Notifiche> findById(int id){	
		return notificheRepo.findById(id);
	}
	
	public Notifiche save(Notifiche notifiche) {
		return notificheRepo.save(notifiche);
	}
	
	public void deleteNotifiche(Notifiche notifiche){
		notificheRepo.delete(notifiche);		
	}

}
