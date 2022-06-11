package com.gsnotes.web.controllers;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Module;
import com.gsnotes.services.IElementServices;
import com.gsnotes.services.IEnseignantServices;
import com.gsnotes.services.IFiliereServices;
import com.gsnotes.services.IModuleServices;
import com.gsnotes.services.INiveauServices;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.web.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cadreadmin")
//Très important car, dans notre Spring security config les URL qui commencent par cadreadmin
// sont dédiées au cadre administratif . Toutes les URL dédinies dans ce controleur définissent des
// fonctionnalités accessibles uniquement au cadre administratif
public class CadreAdministrationController {
    @Autowired
    private IFiliereServices filiereService;// On obtient par injection automatique le service
    @Autowired
    private INiveauServices niveauService;
    @Autowired
    private  IModuleServices moduleService;
    @Autowired
    private IElementServices elementService;
   @Autowired
   private IEnseignantServices enseignantService;
    
    @Autowired
    private HttpSession httpSession;



/**
 * 
 * 
 *@author Ansari rajae
 *
 */
    private Logger LOGGER = LoggerFactory.getLogger(getClass());


    public CadreAdministrationController() {


    }
    //Mrthode de l'ajout d'une filiere (add filiere)
  //Cette méthode initialise le formulaire d'ajout d'une filiere 
    @RequestMapping(value = "showFormCA", method = RequestMethod.GET)//requestMethod.GET car on veut getter les infos d'apres le formulaire
    public String showForm(Model model) {
    	
    	// Nous avons choisit d'utiliser une classe modèle personnalisée
        // définie par FiliereModel pour une meilleur flexibilité
        FiliereModel fmodel = new FiliereModel();
      //On enregistre le modèle pour le passer à la vue
        model.addAttribute("filiereModel", fmodel);

        List<Filiere> filieres = filiereService.getAllFilieres();
        List<FiliereModel> modelFiliere = new ArrayList<FiliereModel>();

        for (int i = 0; i < filieres.size(); i++) {
            FiliereModel fm = new FiliereModel();
            BeanUtils.copyProperties((Filiere) filieres.get(i), fm);
            //pour copier les info properties de la  classe Filiere vers Classe ModelFiliere
          
            modelFiliere.add(fm);

        }
       
      //On ajoute également la liste des filieres  dans le modèle
        model.addAttribute("filiereList", modelFiliere);
       //On ajoute également la liste des coordinateurs  dans le modèle
        model.addAttribute("enseignantList",enseignantService.getAllEnseignants());
        return "cadreadmin/CaForm";
    }
    
  //La création des filieres est implémentée au niveau service
  	//à la couche service
    @RequestMapping(value = "addFiliere", method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute("filiereModel") FiliereModel filiere, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            
            return "cadreadmin/CaForm";
        }
        Filiere fil = new Filiere();
        BeanUtils.copyProperties(filiere, fil);
        filiereService.addFiliere(fil);

        return "redirect:/cadreadmin/showFormCA";
    }
    
    @RequestMapping("manageFilieres")
    public String managePersons(Model model) {

        List<Filiere> filieres = filiereService.getAllFilieres();
        List<FiliereModel> modelFiliere = new ArrayList<FiliereModel>();

        for (int i = 0; i < filieres.size(); i++) {
            FiliereModel fm = new FiliereModel();
            BeanUtils.copyProperties((Filiere) filieres.get(i), fm);
            modelFiliere.add(fm);

        }

        model.addAttribute("filiereList", modelFiliere);

        return "cadreadmin/listFiliere";
    }
    //methode d' update de filiere 
    @RequestMapping(path = "updateFiliereForm/{idFiliere}", method = RequestMethod.GET)
    public ModelAndView updatePersonForm(@PathVariable int idFiliere, Model model) {

        Filiere fil = filiereService.getFiliereById(Long.valueOf(idFiliere));
        // On reoit comme paramètre l'id de la filiere à mettre à jour
        // On construit le modèle
        FiliereModel fm = new FiliereModel();
        BeanUtils.copyProperties(fil, fm);
        model.addAttribute("filiereModel", fm);
        model.addAttribute("enseignantList",enseignantService.getAllEnseignants());
        return new ModelAndView("cadreadmin/updateForm","filiereModel",fm);
    }

    @RequestMapping("updateFiliere")
    public String updateFiliere(@Valid @ModelAttribute("filiereModel") FiliereModel filiereModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateForm";
        }
        System.out.println(filiereModel.toString());
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(filiereModel, filiere);
        filiereService.updateFiliere(filiere);

        // On copie les données du modèle vers l'objet métier puis on appel le service
        // pour faire la mise à jour


        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateForm";
    }
    // la supprision d'une filiere 
    @RequestMapping(value = "deleteFiliere/{idFiliere}", method = RequestMethod.GET)
    public String delete(@PathVariable int idFiliere) {

        filiereService.deleteFiliere(Long.valueOf(idFiliere));

        return "redirect:/cadreadmin/showFormCA";
    }
//Managament niveau 
    @RequestMapping(value = "showFormNiveau", method = RequestMethod.GET)
    public String showFormNiveau(Model model) {

        NiveauModel nmodel = new NiveauModel();
        model.addAttribute("niveauModel", nmodel);

        // Nous avons choisit d'utiliser une classe modèle personnalisée
        // définie par PersonModel pour une meilleur flexibilité

        List<Niveau> niveaus = niveauService.getAllNiveaus();
        List<NiveauModel> modelNiveaus = new ArrayList<NiveauModel>();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < niveaus.size(); i++) {
            NiveauModel niveauModel = new NiveauModel();
            BeanUtils.copyProperties((Niveau) niveaus.get(i), niveauModel);
            modelNiveaus.add(niveauModel);

        }
        model.addAttribute("filiereList",filiereService.getAllFilieres());
        model.addAttribute("niveauList", modelNiveaus);
        return "cadreadmin/formNiveau";
    }
    @RequestMapping(value = "addNiveau", method = RequestMethod.POST)
    public String process(@Valid @ModelAttribute("niveauModel") NiveauModel niveau, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

       if (bindingResult.hasErrors()) {
            return "cadreadmin/formNiveau";
        }
        Niveau niv = new Niveau();
        BeanUtils.copyProperties(niveau, niv);
        niveauService.addNiveau(niv);

        return "redirect:/cadreadmin/showFormNiveau";
    }
    @RequestMapping("manageNiveau")
    public String manageNiveau(Model model) {
        model.addAttribute("niveauList",niveauService.getAllNiveaus());

        return "cadreadmin/listNiveau";
    }
    @RequestMapping(value = "deleteNiveau/{idNiveau}", method = RequestMethod.GET)
    public String deleteNiveau(@PathVariable int idNiveau) {

        niveauService.deleteNiveau(Long.valueOf(idNiveau));

        return "redirect:/cadreadmin/showFormNiveau";
    }
    @RequestMapping(path = "updateNiveauForm/{idNiveau}", method = RequestMethod.GET)
    public ModelAndView updateNiveauForm(@PathVariable int idNiveau, Model model) {

//        Filiere fil = filiereService.getFiliereById(Long.valueOf(idModule));
        Niveau nv = niveauService.getNiveauById(Long.valueOf(idNiveau));
        // On reoit comme paramètre l'id de la personne à mettre à jour

//        System.out.println(idFiliere);
        // On construit le modèle
        NiveauModel nm = new NiveauModel();
        BeanUtils.copyProperties(nv, nm);
        model.addAttribute("niveauModel", nm);
//        System.out.println(model.getAttribute("filiereModel"));
        return new ModelAndView("cadreadmin/updateNiveau","niveauModel",nm);
    }

    @RequestMapping("updateNiveau")
    public String updateNiveau(@Valid @ModelAttribute("niveauModel") NiveauModel niveauModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateNiveau";
        }
        System.out.println(niveauModel.getIdNiveau());
        Niveau nv = new Niveau();
        BeanUtils.copyProperties(niveauModel, nv);
        niveauService.updateNiveau(nv);

        // On copie les données du modèle vers l'objet métier puis on appel le service
        // pour faire la mise à jour


        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateNiveau";
    }
    @RequestMapping(value = "showFormElement", method = RequestMethod.GET)
    public String showFormElement(Model model) {

        ElementModel elementModel = new ElementModel();
        model.addAttribute("elementModel", elementModel);

        List<Element> elements = elementService.getAllElements();
        List<ElementModel> modelElementes = new ArrayList<ElementModel>();
        
        for (int i = 0; i < elements.size(); i++) {
            ElementModel em = new ElementModel();
            BeanUtils.copyProperties((Element) elements.get(i), em);
            modelElementes.add(em);

        }
        model.addAttribute("moduleList",moduleService.getAllModules());
        model.addAttribute("elementList", modelElementes);
        return "cadreadmin/formElement";
    }
    @RequestMapping(value = "addElement", method = RequestMethod.POST)
    public String addElement(@Valid @ModelAttribute("elementModel") ElementModel elementModel, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            // rq.setAttribute("typePerson", +person.getTypePerson());
            return "cadreadmin/formElement";
        }
        Element el = new Element();
        BeanUtils.copyProperties(elementModel, el);
        elementService.addElement(el);


        // rediriger vers l'action shwoForm avec le meme type de personne
        return "redirect:/cadreadmin/showFormElement";
    }
    @RequestMapping(value = "deleteElement/{idMatiere}", method = RequestMethod.GET)
    public String deleteElement(@PathVariable int idMatiere) {

        elementService.deleteElement(Long.valueOf(idMatiere));

        return "redirect:/cadreadmin/showFormElement";
    }
    @RequestMapping(path = "updateElementForm/{idMatiere}", method = RequestMethod.GET)
    public ModelAndView updateElementForm(@PathVariable int idMatiere, Model model) {

//        Filiere fil = filiereService.getFiliereById(Long.valueOf(idModule));
        Element el = elementService.getElementById(Long.valueOf(idMatiere));
        // On reoit comme paramètre l'id de la personne à mettre à jour

//        System.out.println(idFiliere);
        // On construit le modèle
       ElementModel elementModel = new ElementModel();
        BeanUtils.copyProperties(el, elementModel);
        model.addAttribute("elementModel", elementModel);
//        System.out.println(model.getAttribute("filiereModel"));
        return new ModelAndView("cadreadmin/updateElement","elementModel",elementModel);
    }

    @RequestMapping("updateElement")
    public String update(@Valid @ModelAttribute("elementModel") ElementModel elementModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateElement";
        }
        System.out.println(elementModel.toString());
        Element el = new Element();
        BeanUtils.copyProperties(elementModel,el);
       elementService.updateElement(el);

        // On copie les données du modèle vers l'objet métier puis on appel le service
        // pour faire la mise à jour


        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateElement";
    }
    


   
    @RequestMapping(value = "showFormModule", method = RequestMethod.GET)
    public String showFormModule(Model model) {

        ModuleModel nmodel = new ModuleModel();
        model.addAttribute("moduleModel", nmodel);
        List<Module> modules = moduleService.getAllModules();
        List<ModuleModel> modelModules = new ArrayList<ModuleModel>();

       
        for (int i = 0; i < modules.size(); i++) {
            ModuleModel nm = new ModuleModel();
            BeanUtils.copyProperties((Module) modules.get(i), nm);
            modelModules.add(nm);

        }
        model.addAttribute("niveauList",niveauService.getAllNiveaus());
        model.addAttribute("enseignantList",enseignantService.getAllEnseignants());
        model.addAttribute("moduleList", modelModules);
        return "cadreadmin/formModule";
    }
    


    
    
    @RequestMapping(value = "addModule", method = RequestMethod.POST)
    public String addModule(@Valid @ModelAttribute("moduleModel") ModuleModel module, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            return "cadreadmin/formModule";
        }
        Module m = new Module();
        BeanUtils.copyProperties(module, m);
        moduleService.addModule(m);


        return "redirect:/cadreadmin/showFormModule";
    }
   
    @RequestMapping(value = "deleteModule/{idModule}", method = RequestMethod.GET)
    public String deleteModule(@PathVariable int idModule) {

        moduleService.deleteModule(Long.valueOf(idModule));

        return "redirect:/cadreadmin/showFormModule";
    }
   
   
    
    @RequestMapping("manageModules")
    public String manageModules(Model model) {

        List<Module> modules = moduleService.getAllModules();
        List<ModuleModel> moduleModels = new ArrayList<ModuleModel>();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < modules.size(); i++) {
            ModuleModel moduleModel = new ModuleModel();
            BeanUtils.copyProperties((Module)modules.get(i), moduleModel);
            moduleModels.add(moduleModel);

        }

        model.addAttribute("moduleList", moduleModels);

        return "cadreadmin/listModules";
    }
   

    

    @RequestMapping(path = "updateModuleForm/{idModule}", method = RequestMethod.GET)
    public ModelAndView updateModuleForm(@PathVariable int idModule, Model model) {

        Module m = moduleService.getModuleById(Long.valueOf(idModule));
        // On reoit comme paramètre l'id de la personne à mettre à jour

        // On construit le modèle
        ModuleModel moduleModel = new ModuleModel();
        BeanUtils.copyProperties(m, moduleModel);
        model.addAttribute("moduleModel", moduleModel);
        return new ModelAndView("cadreadmin/updateModule","moduleModel",moduleModel);
    }

    @RequestMapping("updateModule")
    public String updatePerson(@Valid @ModelAttribute("moduleModel") ModuleModel moduleModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateModule";
        }
        System.out.println(moduleModel.toString());
       Module modul = new Module();
        BeanUtils.copyProperties(moduleModel, modul);
        moduleService.updateModule(modul);

        // On copie les données du modèle vers l'objet métier puis on appel le service
        // pour faire la mise à jour


        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateModule";
    }
    
   
    
   
}

