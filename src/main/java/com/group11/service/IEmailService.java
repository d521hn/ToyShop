package com.group11.service;

public interface IEmailService {

	void sendRegistrationUserConfirm(String email);

	void sendResetPassword(String email);

}
