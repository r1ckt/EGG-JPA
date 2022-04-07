
package entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Publisher implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "active", nullable = false)
    private Boolean register;

    public Publisher() {
    }

    public Publisher(Integer id, String name, boolean register) {
        this.id = id;
        this.name = name;
        this.register = register;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

   @Override
    public String toString() {
        return String.format("(PUBLISHER) ID: %d, NAME: %s, REGISTERED: %s", id, name, register);
    }
    
    
    
}
