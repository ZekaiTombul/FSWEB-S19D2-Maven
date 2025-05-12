package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll().stream()
                .map(account -> new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount()))
                .toList();
    }

    @GetMapping("/{id}")
    public AccountResponse find(@PathVariable Long id) {
        Account account = accountService.find(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount());
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        Account saved = accountService.save(account);
        return new AccountResponse(saved.getId(), saved.getAccountName(), saved.getMoneyAmount());
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        Account updated = accountService.save(account);
        return new AccountResponse(updated.getId(), updated.getAccountName(), updated.getMoneyAmount());
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id) {
        Account deleted = accountService.delete(id);
        return new AccountResponse(deleted.getId(), deleted.getAccountName(), deleted.getMoneyAmount());
    }
}
