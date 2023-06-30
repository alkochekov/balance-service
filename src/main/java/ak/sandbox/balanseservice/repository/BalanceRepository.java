package ak.sandbox.balanseservice.repository;

import ak.sandbox.balanseservice.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByUser(String user);
}
