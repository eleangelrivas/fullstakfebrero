package com.elengel.api.fullstack.persistence.entity.security;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;
    private String password;

    /*
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;
    */

    @ManyToOne
    @JoinColumn(name="role_id")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

       if(role ==null || role.getPermissions()==null)return null;
    /*
       forma 1
       return role.getPermissions().stream()

               .map(each->{
                  String permission = each.name();
                  return new SimpleGrantedAuthority(permission);
               })
               .collect(Collectors.toList());

               forma 2
               return role.getPermissions().stream()
                .map(each->each.name())
                .map(each->new SimpleGrantedAuthority(e


        formar 3 final de enum antes de usar bd
        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each->each.name())
                .map(each->new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.name()));
        return authorities;

*/

        List<SimpleGrantedAuthority> authorities = role.getPermissions().stream()
                .map(each->each.getOperation().getName())
                .map(each->new SimpleGrantedAuthority(each))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.role.getName()));
        return authorities;





    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
