package com.aj.casino.baccarat;

import com.aj.casino.constant.ErrorCodeConstant;
import com.aj.module.exception.ApiErrorCodeException;

public abstract class BaseController {

public boolean verifyParameter(String[] parameters) throws ApiErrorCodeException {
		
		for(String parameter : parameters) {
			if(parameter==null || parameter.trim().length()==0) {
				 throw new ApiErrorCodeException(ErrorCodeConstant.CODE_MANDATORY_ERROR,
						 ErrorCodeConstant.DESC_MANDATORY_ERROR);
			}
		}
		
		return true;
	}
}
