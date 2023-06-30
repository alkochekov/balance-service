package ak.sandbox.balanseservice.service;

import ak.sandbox.balanseservice.entity.Balance;
import ak.sandbox.balanseservice.entity.History;
import ak.sandbox.balanseservice.repository.BalanceRepository;
import ak.sandbox.balanseservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service

public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public BalanceService(BalanceRepository balanceRepository, HistoryRepository historyRepository) {
        this.balanceRepository = balanceRepository;
        this.historyRepository = historyRepository;
    }
    @Transactional
    public void addNewUser(String user) {
        var balance = new Balance();
        balance.setUser(user);
        balance.setBalance(0.0);
        balanceRepository.save(balance);

        addHistory(balance, "New user created with id :"+balance.getId());
    }
    @Transactional
    public void increaseBalance(String user, double amount) {
        var balance = balanceRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + user));

        double newBalance = balance.getBalance() + amount;
        balance.setBalance(newBalance);

        balanceRepository.save(balance);
        addHistory(balance, "Balance increased by " + amount);
    }
    @Transactional
    public void decreaseBalance(String user, double amount) {
        Balance balance = balanceRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + user));

        double newBalance = balance.getBalance() - amount;
        if (newBalance < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance.setBalance(newBalance);

        balanceRepository.save(balance);
        addHistory(balance, "Balance decreased by " + amount);
    }


    public double getBalance(String user) {
        Balance balance = balanceRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + user));

        return balance.getBalance();
    }

    private void addHistory(Balance balance, String operation) {
        var history = new History();
        history.setBalance(balance);
        history.setOperation(operation);
        history.setDateTime(LocalDateTime.now());
        historyRepository.save(history);
    }
}