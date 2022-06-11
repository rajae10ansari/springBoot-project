package com.gsnotes.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsnotes.bo.Enseignant;
import com.gsnotes.dao.IEnseignantDao;
import com.gsnotes.services.IEnseignantServices;

import java.util.List;

import javax.transaction.Transactional;
@Service
@Transactional
public class EnseignantServiceImpl implements IEnseignantServices {
	    @Autowired
	    private IEnseignantDao enseignantDao;

	    @Override
	    public Enseignant getEnseignantById(Long id) {
	        return enseignantDao.getById(id);
	    }

	    public List<Enseignant> getAllEnseignants() {
	        return enseignantDao.findAll();
	    }
	}


