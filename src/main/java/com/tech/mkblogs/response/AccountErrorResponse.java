package com.tech.mkblogs.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountErrorResponse {

    private List<ErrorObject> errorList = new ArrayList<>();
 	public void addErrorObject(ErrorObject object) {
		errorList.add(object);
	}
}
