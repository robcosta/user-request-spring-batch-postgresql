package com.robertocosta.userrequestspringbatch.domain;

import java.util.List;

import com.robertocosta.userrequestspringbatch.dto.UserDTO;

public class ResponseUser {

	private List<UserDTO> content;
	
	public List<UserDTO> getContent() {
		return content;
	}
}
