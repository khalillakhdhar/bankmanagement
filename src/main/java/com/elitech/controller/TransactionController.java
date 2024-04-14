package com.elitech.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elitech.model.dto.TransactionDto;
import com.elitech.model.mappers.TransactionMapper;
import com.elitech.services.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {
 private final	TransactionService transactionService;
 @GetMapping
 public ResponseEntity<Page<TransactionDto>> getTransactions(Pageable pageable)
 {
	 return ResponseEntity.ok().body(transactionService.getAllTransactions(pageable));
 }
 @PostMapping
 public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transactionDto, @RequestParam(defaultValue = "0",required = false) long idSource,@RequestParam(defaultValue = "0",required = false) long idDestination)
 {
	 TransactionDto transact=transactionService.addTransaction(idSource, idDestination, TransactionMapper.convertToEntity(transactionDto));
	 return new ResponseEntity<>(transact, HttpStatus.CREATED);
	 
 }
 @GetMapping("/{id}")
 public ResponseEntity<TransactionDto> getOneTransaction(@PathVariable long id)
 {
	 return ResponseEntity.ok(transactionService.getTransactionById(id));
 }
	
	

}
