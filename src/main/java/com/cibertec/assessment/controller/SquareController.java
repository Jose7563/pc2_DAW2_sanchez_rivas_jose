package com.cibertec.assessment.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.assessment.model.Square;
import com.cibertec.assessment.service.SquareService;


@RestController
@RequestMapping("/cuadrado")
public class SquareController {


	@Autowired
    private SquareService squareService;

	@PostMapping("/crear")
    public ResponseEntity<Square> crear(@RequestBody Square square) {
        
        return new ResponseEntity<>(squareService.create(square), HttpStatus.CREATED);
    }

	@GetMapping("/list")
    public ResponseEntity<List<Square>> lista() {
        return new ResponseEntity<>(squareService.list(), HttpStatus.OK);
    }
}
