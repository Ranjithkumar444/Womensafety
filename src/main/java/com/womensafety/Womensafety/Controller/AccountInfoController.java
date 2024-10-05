package com.womensafety.Womensafety.Controller;

import com.womensafety.Womensafety.Model.AccountInfo;
import com.womensafety.Womensafety.Model.User;
import com.womensafety.Womensafety.Service.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountInfoController {
    @Autowired
    private AccountInfoService accountInfoService;

    @GetMapping("/{usersId}")
    public ResponseEntity<AccountInfo> getAccountInfo(@PathVariable Long usersId) {
        return ResponseEntity.ok(accountInfoService.getAccountInfo(usersId));
    }

    @PostMapping("/{usersId}")
    public ResponseEntity<AccountInfo> updateAccountInfo(@PathVariable Long usersId, @RequestBody AccountInfo accountInfo) {
        accountInfo.setUser(new User(usersId));
        return ResponseEntity.ok(accountInfoService.saveAccountInfo(accountInfo));
    }
}
