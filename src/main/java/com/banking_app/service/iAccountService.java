package com.banking_app.service;

import com.banking_app.dto.AccountDto;

import java.util.List;

public interface iAccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto depositAmount(Long id, double amount);

    AccountDto withDraw(Long id, double amount);

    List<AccountDto> getAllAccount();

    void deleteAccount(Long id);


}
