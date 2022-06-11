package com.gsnotes.services;

import com.gsnotes.bo.Filiere;
import com.gsnotes.bo.Niveau;
import com.gsnotes.dao.INiveauDao;
import com.gsnotes.utils.export.ExcelExporter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INiveauServices{
        public void addNiveau(Niveau niveau);

        public void updateNiveau(Niveau niveau);

        public List<Niveau> getAllNiveaus();

        public void deleteNiveau(Long id);

        public Niveau getNiveauById(Long id);


        public ExcelExporter prepareFiliereExport(List<Niveau> niveaus);


}
