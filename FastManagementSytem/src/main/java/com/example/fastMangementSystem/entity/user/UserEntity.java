package com.example.fastMangementSystem.entity.user;

import com.example.fastMangementSystem.entity.BaseEntity;
import com.example.fastMangementSystem.entity.card.CardEntity;
import com.example.fastMangementSystem.entity.course.CourseEntity;
import com.example.fastMangementSystem.entity.groups.GroupEntity;
import com.example.fastMangementSystem.entity.history.HistoryEntity;
import com.example.fastMangementSystem.entity.order.OrderEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.Ordered;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity implements UserDetails {
    private String name;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private List<UserRole> roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<GroupEntity> mentorCourses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CourseEntity> adminCourses;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderEntity> studentOrders;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CardEntity> cards;
    @OneToMany(cascade = CascadeType.ALL)
    private List<HistoryEntity> histories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE="ROLE_";
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE + role.name()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
