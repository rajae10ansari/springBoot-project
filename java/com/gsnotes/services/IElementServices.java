package com.gsnotes.services;

import com.gsnotes.bo.Element;
import com.gsnotes.bo.Filiere;
import com.gsnotes.utils.export.ExcelExporter;

import java.util.List;

public interface IElementServices {
    public void addElement(Element element);

    public void updateElement(Element element);

    public List<Element> getAllElements();

    public void deleteElement(Long id);

    public Element getElementById(Long id);


    public ExcelExporter prepareElementExport(List<Element> elements);



}
