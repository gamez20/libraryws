package com.project.Library.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class LoanBooksDTO {

    private Integer idLoan;
    private Integer userIdentification;
    private Integer userType;
    private String isbn;
    private String maximumDateDevolution;

}