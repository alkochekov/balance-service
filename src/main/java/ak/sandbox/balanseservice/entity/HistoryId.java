package ak.sandbox.balanseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoryId implements Serializable{

@Column(name = "id")
private Long id;

@Column(name = "user")
private String user;
 }
