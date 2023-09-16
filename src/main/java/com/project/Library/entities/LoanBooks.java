package com.project.Library.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "loan_books")
public class LoanBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loan")
    private Integer idLoan;

    @Column(name = "user_identification")
    private Integer userIdentification;

    @Column(name = "user_type")
    private Integer userType;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "maximum_date_devolution")
    private String maximumDateDevolution;
}
