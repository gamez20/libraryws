package com.project.Library.services;

import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooksDTO;
import com.project.Library.entities.LoanBooks;


public interface LoanBooksService {

    ResponseLoanBooksDTO createNewLoan(LoanBooks loanBooks);
    LoanBooksDTO findLoanById(Integer loandId);

}
