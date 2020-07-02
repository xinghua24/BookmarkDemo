package com.xinghua24.bookmark.repo;

import com.xinghua24.bookmark.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    public List<User> findByProviderAndProviderId(String provider, String providerId);
}
