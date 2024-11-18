package com.example.slstudiomini.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.slstudiomini.model.Authority;
import com.example.slstudiomini.model.User;
import com.example.slstudiomini.repository.AuthorityRepository;
import com.example.slstudiomini.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAllUsers() {
        // return userRepository.findAll();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> user = cq.from(User.class);
        
        cq.select(user);
        cq.where(
            cb.equal(user.get(), user)
        );
    }
    public void saveUser(User user) {
        userRepository.save(user);
    }

    //addするとき
    public User addEnableStudentAndHashPassword(User user) {
        //有効化
        user.setEnabled(true);
        //ハッシュ化するクラスの準備
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //ハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        //パスワードの詰めなおし
        user.setPassword(hashedPassword);

        Authority authority = authorityRepository.findByAuthority("ROLE_USER")
            .orElseThrow(() -> new EntityNotFoundException("Authority Not Found with name=USER"));

        user.setAuthorities(Set.of(authority));

        return userRepository.save(user);
    }
}
