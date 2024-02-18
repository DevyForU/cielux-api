package com.cielux.api.repository.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@Entity
@Table(name = "File")
@NoArgsConstructor
public class File implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String type;
  private BigInteger size;
  private Timestamp creation_date;
  private Timestamp modification_date;

  @ManyToOne
  @JoinColumn(name = "\"User_id\"", updatable = false)
  private User user;

  public void setModificationDate() {
    this.modification_date = Timestamp.from(Instant.now());
  }
}
