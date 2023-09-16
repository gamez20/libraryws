package com.project.Library.controllers;


import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooks;
import com.project.Library.entities.LoanBooks;
import com.project.Library.services.LoanBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/library")
public class LoanBooksController {

    private final LoanBooksService loanBooksService;
    @Autowired
    public LoanBooksController(LoanBooksService loanBooksService) {
        this.loanBooksService = loanBooksService;
    }

    @PostMapping("/prestamo")
    public ResponseEntity<?> crearPrestamo(@RequestBody LoanBooks loanBooks) {
        ResponseLoanBooks responseLoanBooks = loanBooksService.createNewLoan(loanBooks);
        if (responseLoanBooks == null){
            return new ResponseEntity<>("\"mensaje\" : \"El usuario con identificación xxxxxx ya tiene un libro prestado por lo cual no se le\n" +
                    "puede realizar otro préstamo\"", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(responseLoanBooks, HttpStatus.OK);
    }

    @GetMapping("/prestamo/{id-prestamo}")
    private ResponseEntity<LoanBooksDTO> findLoanById(@PathVariable("id-prestamo") Integer loanId){
        LoanBooksDTO loanBooksDTOs = loanBooksService.findLoanById(loanId);
        return new ResponseEntity<>(loanBooksDTOs, HttpStatus.OK);
    }

}
