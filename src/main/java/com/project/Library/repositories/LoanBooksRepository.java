package com.project.Library.repositories;

import com.project.Library.entities.LoanBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanBooksRepository extends JpaRepository<LoanBooks, Integer> {

}
