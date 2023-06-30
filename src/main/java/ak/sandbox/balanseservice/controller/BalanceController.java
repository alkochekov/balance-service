package ak.sandbox.balanseservice.controller;

import ak.sandbox.balanseservice.service.BalanceService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<String> addNewUser(@RequestParam @NonNull String user) {
        balanceService.addNewUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping("/increaseBalance")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<String> increaseBalance(@RequestParam @NonNull String user, @RequestParam @NonNull Double amount) {
        balanceService.increaseBalance(user, amount);
        return ResponseEntity.ok("Balance increased successfully");
    }

    @PostMapping("/decreaseBalance")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
    public ResponseEntity<String> decreaseBalance(@RequestParam @NonNull String user, @RequestParam @NonNull Double amount) {
        try {
            balanceService.decreaseBalance(user, amount);
            return ResponseEntity.ok("Balance decreased successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBalance")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Double> getBalance(@RequestParam @NonNull String user) {
        double balance = balanceService.getBalance(user);
        return ResponseEntity.ok(balance);
    }
}
