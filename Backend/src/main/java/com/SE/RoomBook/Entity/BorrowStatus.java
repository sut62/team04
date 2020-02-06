package com.SE.RoomBook.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
public class BorrowStatus {

    @Id
    @SequenceGenerator(name = "BorrowStatus_SEQ", sequenceName = "BorrowStatus_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BorrowStatus_SEQ")
    @Column(name = "BorrowStatus_id", unique = true, nullable = true)
    private @NonNull Long borstatus_id;

    @NotNull
    private String borstatus;
}

