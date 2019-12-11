package com.okta.springbootvue.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="RETURN")
public class Return {
    
	@Id
	@SequenceGenerator(name="return_seq",sequenceName="return_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="return_seq")
	@Column(name="RETURN_ID",unique = true, nullable = true)
    private @NonNull Long id;
}