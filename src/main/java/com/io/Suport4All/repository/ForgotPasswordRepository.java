package com.io.Suport4All.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.ForgotPassword;
import com.io.Suport4All.entity.UsuarioEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer>{
	
//	@Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
//	Optional<ForgotPassword> findByOtpAndUser(Integer otp, UsuarioEntity user);
			
	@Query("SELECT fp FROM ForgotPassword fp WHERE fp.token = ?1")
	Optional<ForgotPassword> findByToken(String token);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM forgot_password WHERE user_id = ?1", nativeQuery = true)
	void deleteByUser(Long userId);
	

}
