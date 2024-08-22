package com.banking_app.mapper;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getAccHolderName(),
                accountDto.getBalance()
        );
        return account;

    }

    public static AccountDto maptoAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
