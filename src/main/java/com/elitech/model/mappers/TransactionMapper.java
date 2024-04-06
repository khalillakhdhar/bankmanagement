package com.elitech.model.mappers;

import org.modelmapper.ModelMapper;

import com.elitech.model.dto.TransactionDto;
import com.elitech.model.entities.Transaction;

public class TransactionMapper {
	private static final ModelMapper modelMapper= new ModelMapper();
	public static TransactionDto convertToDTO(Transaction transaction)
	{
	return modelMapper.map(transaction, TransactionDto.class);
	}

	public static Transaction convertToEntity(TransactionDto transactionDTO)
	{
	return modelMapper.map(transactionDTO, Transaction.class);	
	}
}
