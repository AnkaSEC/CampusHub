package co.ankasec.campushub.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("STUDENT")

public class Student extends User {

    @Column(nullable = false)
    private String studentNumber;

    @Column(nullable = false)
    private String department;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Club> clubs = new ArrayList<>();
}
