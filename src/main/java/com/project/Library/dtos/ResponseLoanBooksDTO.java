package com.project.Library.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ResponseLoanBooksDTO {
    public ResponseLoanBooksDTO() {
    }

    private Integer id;
    private String dueDay;
}
