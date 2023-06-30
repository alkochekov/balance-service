package ak.sandbox.balanseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "balances")
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user")
    private String user;

    @Column(name = "balance")
    private Double balance;

    @OneToMany(mappedBy = "balance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<History> historyList;
}
