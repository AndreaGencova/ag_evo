package com.ag_evo.service;

import com.ag_evo.model.Users;

import java.util.List;

public interface PolicyService {
    public List<String> resolvePolicies(Users user);
}
