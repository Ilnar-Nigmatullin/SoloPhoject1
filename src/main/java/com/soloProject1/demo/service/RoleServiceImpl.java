package com.soloProject1.demo.service;

import com.soloProject1.demo.dao.RoleDAO;
import com.soloProject1.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByName(String role) {
        return roleDAO.getRoleByName(role);
    }

}
