package com.tech.mkblogs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.security.db.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mappings({
	      @Mapping(target="id", source="user.id"),
	      @Mapping(target="loginName", source="user.loginName"),
	      @Mapping(target="lastLogin", source="user.lastLogin"),
	      
	      @Mapping(target="connectionType", source="user.settings.connectionType"),
	      @Mapping(target="primaryKeyGenerationType", source="user.settings.primaryKeyGenerationType"),
	      @Mapping(target="authenticationType", source="user.settings.authenticationType"),
	      @Mapping(target="authenticationEncrypted", source="user.settings.authenticationEncrypted")
	    })
	UserSessionDTO toUserDTO(User user);
	
	
}