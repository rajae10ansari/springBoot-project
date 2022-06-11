package com.gsnotes.services;

import com.gsnotes.bo.Filiere;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.utils.export.ExcelExporter;

import java.util.List;

public interface IFiliereServices {
    public void addFiliere(Filiere filiere);

    public void updateFiliere(Filiere filiere);

    public List<Filiere> getAllFilieres();

    public void deleteFiliere(Long id);

    public Filiere getFiliereById(Long id);



}
