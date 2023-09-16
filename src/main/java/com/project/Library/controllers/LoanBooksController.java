package com.project.Library.controllers;


import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooksDTO;
import com.project.Library.entities.LoanBooks;
import com.project.Library.services.LoanBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/library")
public class LoanBooksController {

    private final LoanBooksService loanBooksService;
    @Autowired
    public LoanBooksController(LoanBooksService loanBooksService) {
        this.loanBooksService = loanBooksService;
    }

    @PostMapping("/prestamo")
    public ResponseEntity<?> createLoan(@RequestBody LoanBooks loanBooks) {
        ResponseLoanBooksDTO responseLoanBooksDTO = loanBooksService.createNewLoan(loanBooks);
        if (responseLoanBooksDTO.getId() == null){
            return new ResponseEntity<>("\"mensaje\" : \"El usuario con identificación: " + loanBooks.getUserIdentification() +
                    " ya tiene un libro prestado por lo cual no se le\n" +
                    "puede realizar otro préstamo\"", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseLoanBooksDTO, HttpStatus.OK);
    }

    @GetMapping("/prestamo/{id-prestamo}")
    private ResponseEntity<LoanBooksDTO> findLoanById(@PathVariable("id-prestamo") Integer loanId){
        LoanBooksDTO loanBooksDTOs = loanBooksService.findLoanById(loanId);
        return new ResponseEntity<>(loanBooksDTOs, HttpStatus.OK);
    }

}
