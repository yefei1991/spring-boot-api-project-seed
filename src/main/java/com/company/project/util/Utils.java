package com.company.project.util;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;

public class Utils {

	public static void ServiceException(String message) {
		throw new ServiceException(message);
	}

	public static Result success() {
		return ResultGenerator.genSuccessResult();
	}
	
	public static Result success(Object data) {
		return ResultGenerator.genSuccessResult(data);
	}
	
	public static Result failure(String message) {
		return ResultGenerator.genFailResult(message);
	}
}
