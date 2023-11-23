package vn.edu.iuh.fit.week_06.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String passwordHash;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(columnDefinition = "TEXT")
    private String intro;

    @Column(columnDefinition = "TEXT")
    private String profile;

    @OneToMany(mappedBy = "author")
    private List<Post> post;
}