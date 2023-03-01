package relex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    @NotNull
    private String username;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String secretkey;


    public User(){}
    public User(String email,String userName,String secretkey){
        this.username=userName;
        this.email=email;
        this.secretkey=secretkey;
    }
}
