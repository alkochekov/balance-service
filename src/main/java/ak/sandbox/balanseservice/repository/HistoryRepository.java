package ak.sandbox.balanseservice.repository;

import ak.sandbox.balanseservice.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
