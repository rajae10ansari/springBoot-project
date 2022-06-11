package com.gsnotes.services.impl;

import com.gsnotes.bo.Filiere;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.dao.iFiliereDao;
import com.gsnotes.dao.IUtilisateurDao;
import com.gsnotes.services.IFiliereServices;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class FiliereServicesImpl implements IFiliereServices {

    @Autowired
    private iFiliereDao filiereDao;

    public List<Filiere> getAllFilieres() {

        return filiereDao.findAll();
    }

    @Override
    public void deleteFiliere(Long id) {
        filiereDao.deleteById(id);
    }

    public Filiere getFiliereById(Long id) {
        return filiereDao.getById(id);

    }
    @Override
    public void addFiliere(Filiere filiere) {
        filiereDao.save(filiere);

    }

    @Override
    public void updateFiliere(Filiere filiere) {
        filiereDao.save(filiere);

    }
    
   
}
