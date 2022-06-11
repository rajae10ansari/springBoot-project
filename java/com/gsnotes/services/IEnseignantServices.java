package com.gsnotes.services;

import com.gsnotes.bo.Enseignant;
import java.util.List;

public interface IEnseignantServices {
	    public Enseignant getEnseignantById(Long id);
	    public  List<Enseignant> getAllEnseignants();
			
	

	

}
