package com.company.service;

import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.exception.TicketingProjectException;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    UserDTO save(UserDTO dto) throws TicketingProjectException;
    UserDTO update(UserDTO dto) throws TicketingProjectException;
    void delete(String username) throws TicketingProjectException;

    void deleteByUserName(String username);

    List<UserDTO> listAllByRole(String role);

    Boolean checkIfUserCanBeDeleted(User user);

    UserDTO confirm(User user);


}
