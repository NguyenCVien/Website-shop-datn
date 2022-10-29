package com.websiteshop.service;

import java.util.List;

import com.websiteshop.entity.Account;

public interface AccountService {
    Account findById(String username);

}
