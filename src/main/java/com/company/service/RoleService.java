package com.company.service;

import com.company.dto.RoleDTO;
import com.company.exception.TicketingProjectException;

import java.util.List;

public interface RoleService {

    List<RoleDTO> listAllRoles();
    RoleDTO findById(Long id) throws TicketingProjectException;
}
