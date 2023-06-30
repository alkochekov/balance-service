package ak.sandbox.balanseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @EmbeddedId
    private HistoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "balance_id", referencedColumnName = "id", insertable = false, updatable = false),
            @JoinColumn(name = "user", referencedColumnName = "user", insertable = false, updatable = false)
    })
    private Balance balance;

    @Column(name = "operation")
    private String operation;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

}

