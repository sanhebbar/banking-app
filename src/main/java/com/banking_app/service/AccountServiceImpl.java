package com.banking_app.service;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;
import com.banking_app.mapper.AccountMapper;
import com.banking_app.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements iAccountService {
    @Autowired
    AccountRepo accountRepo;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedaccount = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(savedaccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account not found"));
        return AccountMapper.maptoAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account not found"));
        Double total = account.getBalance() + amount;
        account.setBalance(total);
        Account save = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(save);
    }

    @Override
    public AccountDto withDraw(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account not found"));
       if(account.getBalance() <  amount){
           throw new RuntimeException("insufficient amount");
       }
       Double total = account.getBalance() - amount;
        account.setBalance(total);
        Account save = accountRepo.save(account);
        return AccountMapper.maptoAccountDto(save);

    }

    @Override
    public List<AccountDto> getAllAccount() {
       List<Account> account = accountRepo.findAll();
       return account.stream().map(AccountMapper::maptoAccountDto).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account not found"));
         accountRepo.deleteById(id);

    }




}
