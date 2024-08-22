package com.banking_app.controller;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;
import com.banking_app.repo.AccountRepo;
import com.banking_app.service.AccountServiceImpl;
import com.banking_app.service.iAccountService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    iAccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getProductById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.depositAmount(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withDraw(@PathVariable Long id, @RequestBody Map<String, Double> request ){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withDraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto> > getAllAccount(){
        List<AccountDto> accountDto = accountService.getAllAccount();
        return ResponseEntity.ok(accountDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("account deleted");
    }
}
