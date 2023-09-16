package com.project.Library.services.impl;

import com.project.Library.dtos.LoanBooksDTO;
import com.project.Library.dtos.ResponseLoanBooksDTO;
import com.project.Library.entities.LoanBooks;
import com.project.Library.repositories.LoanBooksRepository;
import com.project.Library.services.LoanBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanBooksServiceImpl implements LoanBooksService {
    private final LoanBooksRepository loanBooksRepository;

    @Autowired
    public LoanBooksServiceImpl(LoanBooksRepository loanBooksRepository) {
        this.loanBooksRepository = loanBooksRepository;
    }

    @Override
    public ResponseLoanBooksDTO createNewLoan(LoanBooks loanBooks) {
        ResponseLoanBooksDTO responseLoanBooksDTO = new ResponseLoanBooksDTO();
        LoanBooks loanBooksResponse = null;
        LocalDate fechaActual = LocalDate.now();
        List<LoanBooks> loanBooksResponseLoanBooksList = new ArrayList<>();
        LocalDate fechaDevolucion = fechaActual;

        if(loanBooks.getUserType() == 1){
            loanBooksResponseLoanBooksList =  loanBooksRepository.findByUserIdentification(loanBooks.getUserIdentification());
        }
        assert loanBooksResponseLoanBooksList != null;
        if (loanBooksResponseLoanBooksList.isEmpty()){
            fechaDevolucion = fechaDevolucion.plusDays(10);

            while (fechaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY || fechaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fechaDevolucion = fechaDevolucion.plusDays(1);
            }
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy");
            String dateFormatDevolution = fechaDevolucion.format(format);
            loanBooks.setMaximumDateDevolution(dateFormatDevolution);
            loanBooksResponse = loanBooksRepository.save(loanBooks);

            responseLoanBooksDTO.setId(loanBooksResponse.getIdLoan());
            responseLoanBooksDTO.setDueDay(loanBooksResponse.getMaximumDateDevolution());
        }

        if (loanBooks.getUserType() != 1){
            switch (loanBooks.getUserType()) {
                case 2:
                    fechaDevolucion = fechaDevolucion.plusDays(8);
                    break;
                case 3:
                    fechaDevolucion = fechaDevolucion.plusDays(7);
                    break;
                default:
                    break;
            }
            while (fechaDevolucion.getDayOfWeek() == DayOfWeek.SATURDAY || fechaDevolucion.getDayOfWeek() == DayOfWeek.SUNDAY) {
                fechaDevolucion = fechaDevolucion.plusDays(1);
            }
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy");
            String dateFormatDevolution = fechaDevolucion.format(format);
            loanBooks.setMaximumDateDevolution(dateFormatDevolution);
            loanBooksResponse = loanBooksRepository.save(loanBooks);

            responseLoanBooksDTO.setId(loanBooksResponse.getIdLoan());
            responseLoanBooksDTO.setDueDay(loanBooksResponse.getMaximumDateDevolution());
        }

        return responseLoanBooksDTO;
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
