package com.elitech.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elitech.model.dto.CompteDto;

public interface CompteService {
public Page<CompteDto> getAllComptes(Pageable pageable);
public void deleteOne(long id);
public CompteDto getCompteById(long id);
}
