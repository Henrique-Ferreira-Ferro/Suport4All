package com.io.Suport4All.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.io.Suport4All.dto.ChangePassword;
import com.io.Suport4All.dto.MailBody;
import com.io.Suport4All.entity.ForgotPassword;
import com.io.Suport4All.entity.UsuarioEntity;
import com.io.Suport4All.exceptions.BadRequestException;
import com.io.Suport4All.repository.ForgotPasswordRepository;
import com.io.Suport4All.repository.UsuarioRepository;
import com.io.Suport4All.service.EmailService;

@RestController
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

	private final UsuarioRepository userRepository;
	private final EmailService emailService;
	private final ForgotPasswordRepository forgotPasswordRepository;

	private final PasswordEncoder passwordEncoder;

	public ForgotPasswordController(UsuarioRepository userRepository, 
			EmailService emailService, ForgotPasswordRepository forgotPasswordRepository,PasswordEncoder  passwordEncoder) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.forgotPasswordRepository = forgotPasswordRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Envio de email para verificar conta

	@PostMapping("/verifyMail/{email}")
	public ResponseEntity<String> verifyEmail(@PathVariable String email) {
		UsuarioEntity user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Please provide valid email"));

		int otp = otpGenerator();

		MailBody mailBody = MailBody.builder().to(email).text("THis is the OTP for your Forgot Password request " + otp)
				.subject("OTP for Forgot Password Request").build();

		ForgotPassword fp = ForgotPassword.builder().otp(otp)
				.expirationTime(new Date(System.currentTimeMillis() + 70 * 10000)).user(user).build();

		emailService.enviarEmail(mailBody);
		forgotPasswordRepository.save(fp);

		return ResponseEntity.ok("Email sent for verification!");

	}

	@PostMapping("/verifyOtp/{otp}/{email}")	
	public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email){
		UsuarioEntity user = userRepository.findByEmail(email).orElseThrow
				(() -> new BadRequestException("Please provide valid email"));
		
	ForgotPassword fp =  forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new BadRequestException("Please provide na valid email!"+ email));
	
	if(fp.getExpirationTime().before(Date.from(Instant.now()))){
	
	forgotPasswordRepository.deleteById(fp.getFpid());

	return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
	
}

	return ResponseEntity.ok("OTP verified!");

}

//Para a troca da senha do usuário

	@PostMapping("/changePassword/{email}")
	public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
			@PathVariable String email) {

		if (!Objects.equals(changePassword.getPassword(), changePassword.getRepeatPassword())) {

			return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
		}
		String encodedPassword = passwordEncoder.encode(changePassword.getPassword());

		userRepository.updatePassword(email, encodedPassword);

		return ResponseEntity.ok("Password has been changed!");
	}

	private Integer otpGenerator() {

		Random random = new Random();
		return random.nextInt(100_000, 999_999);
	}

}
