package com.project.Library.services.impl;

import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooks;
import com.project.Library.entities.LoanBooks;
import com.project.Library.repositories.LoanBooksRepository;
import com.project.Library.services.LoanBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanBooksServiceImpl implements LoanBooksService {
    private final LoanBooksRepository loanBooksRepository;

    @Autowired
    public LoanBooksServiceImpl(LoanBooksRepository loanBooksRepository) {
        this.loanBooksRepository = loanBooksRepository;
    }

    @Override
    public ResponseLoanBooks createNewLoan(LoanBooks loanBooks) {

        ResponseLoanBooks responseLoanBooks = ResponseLoanBooks.builder().build();
        LoanBooks loanBooksResponse = loanBooksRepository.save(loanBooks);

        responseLoanBooks.setId(loanBooksResponse.getIdLoan());
        responseLoanBooks.setDueDay(loanBooksResponse.getMaximumDateDevolution());

        return responseLoanBooks;
    }

    @Override
    public LoanBooksDTO findLoanById(Integer loandId) {
        LoanBooksDTO loanBooksDTO = LoanBooksDTO.builder().build();
        Optional<LoanBooks> loanBooks = loanBooksRepository.findById(loandId);
        if (loanBooks.isPresent()){
            loanBooksDTO = mapToDTO(loanBooks);
        }
        return loanBooksDTO;
    }

    private LoanBooksDTO mapToDTO(Optional<LoanBooks> loanBooks) {
        LoanBooksDTO loanBooksDTO = LoanBooksDTO.builder().build();

        loanBooksDTO.setIdLoan(loanBooks.get().getIdLoan());
        loanBooksDTO.setUserIdentification(loanBooks.get().getUserIdentification());
        loanBooksDTO.setUserType(loanBooks.get().getUserType());
        loanBooksDTO.setIsbn(loanBooks.get().getIsbn());
        loanBooksDTO.setMaximumDateDevolution(loanBooks.get().getMaximumDateDevolution());

        return loanBooksDTO;
    }
}
