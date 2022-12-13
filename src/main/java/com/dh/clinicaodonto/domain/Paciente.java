package com.dh.clinicaodonto.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Paciente {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(nullable = false, length = 50)
   private String nome;
   @Column(nullable = false, length = 250)
   private String sobrenome;
   @Column(unique = true, nullable = false,length = 10)
   private String rg;
   @Column(nullable = false)
   private LocalDate dataCadastro;
   @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
   @JoinColumn(name = "endereco_id")
   private Endereco endereco;
   @OneToOne(cascade = CascadeType.PERSIST)
   @JoinColumn(name = "usuario_id")
   private Usuario usuario;
}
