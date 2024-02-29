package com.cibertec.assessment.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.assessment.beans.PolygonBean;
import com.cibertec.assessment.beans.Utilitario;
import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.repo.SquareRepo;
import com.cibertec.assessment.service.PolygonService;
import com.cibertec.assessment.service.SquareService;

@Service
public class SquareServiceImpl implements SquareService{

	@Autowired 
	SquareRepo squareRepo;
	
	@Autowired
	PolygonService polygonService;
	
	//Al momento de crear se debe validar si 
	//alguno de parte del cuadrado se encuentra dentro de algun
	//poligono y de ser asi se debe capturar el id de los poligonos y 
	//guardar como un string pero con formato de array
	//Ejemplo polygons = "["1","2"] cumplen con la condicion"
	//Se guardan los ids correspondites
	//usar los metodos ya existentes para listar polygonos
	
	Utilitario util = new Utilitario();
	List<Integer> ids = new ArrayList<>();
	@Override
    public Square create(Square s) {
	    List<PolygonBean> polygons = polygonService.list();
	    // se verifica la interseccion
	    for (PolygonBean polygon : polygons) {
	        if (util.intersectoEntreFiguras(util.convertStringInIntegerArray(s.getXPoints()),
	        		util.convertStringInIntegerArray(s.getYPoints()) , polygon)) {
	            ids.add(polygon.getId());
	        }
	    }
	   
	    s.setPolygons(ids.toString()); 

	    return squareRepo.save(s);
    }

	
	@Override
    public List<Square> list() {
        return squareRepo.findAll();
    }

   
	

}
