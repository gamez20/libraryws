package com.project.Library.services;

import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooks;
import com.project.Library.entities.LoanBooks;

import java.util.Optional;


public interface LoanBooksService {

    ResponseLoanBooks createNewLoan(LoanBooks loanBooks);
    LoanBooksDTO findLoanById(Integer loandId);

}
