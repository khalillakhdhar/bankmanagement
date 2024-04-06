package com.elitech.model.mappers;

import org.modelmapper.ModelMapper;

import com.elitech.model.dto.ProfileDto;
import com.elitech.model.entities.Profile;

public class ProfileMapper {
	private static final ModelMapper modelMapper= new ModelMapper();
	public static ProfileDto convertToDTO(Profile profile)
	{
	return modelMapper.map(profile, ProfileDto.class);
	}

	public static Profile convertToEntity(ProfileDto profileDTO)
	{
	return modelMapper.map(profileDTO, Profile.class);	
	}
}
