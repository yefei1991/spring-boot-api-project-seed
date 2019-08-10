package com.company.project.core;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;

public class AbstractController {

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected HttpServletResponse response;

  public Page<?> getRequestPage() {
    Page<?> page = new Page<Integer>(1, 10);
    page.setPageNum(Integer.parseInt(request.getParameter("current")));
    page.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
    return page;
  }

  public Map<String, String> getParamMap() {
    Map<String, String> params = new HashMap<>();
    request.getParameterMap().forEach((key, value) -> {
      params.put(key, value[0].trim());
    });
    return params;
  }

}
