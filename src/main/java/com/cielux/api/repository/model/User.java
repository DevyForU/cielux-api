package com.cielux.api.repository.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String email;
  private String password;

  @OneToMany(mappedBy = "\"User\"")
  private List<File> files;

  @OneToMany(mappedBy = "\"User\"")
  private List<Folder> folders;

}
