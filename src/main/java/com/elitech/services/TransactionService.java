package com.elitech.services;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elitech.model.dto.TransactionDto;
import com.elitech.model.entities.Transaction;

public interface TransactionService {
public Page<TransactionDto> getAllTransactions(Pageable pageable );
public TransactionDto getTransactionById(long id);
public TransactionDto addTransaction(long idSource,long idDestination,Transaction transaction);
public void updateSolde(double montant,long idCompte);

}
