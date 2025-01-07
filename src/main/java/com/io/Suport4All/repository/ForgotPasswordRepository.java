package com.io.Suport4All.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.io.Suport4All.entity.ForgotPassword;
import com.io.Suport4All.entity.UsuarioEntity;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer>{
	
	@Query("select fp from ForgotPassword fp where fp.otp = ?1 and fp.user = ?2")
	Optional<ForgotPassword> findByOtpAndUser(Integer otp, UsuarioEntity user);
			
}
