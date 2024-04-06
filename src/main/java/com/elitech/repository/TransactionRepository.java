package com.elitech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitech.model.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
