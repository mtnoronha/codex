package com.sifionsolution.codex.controller;

import static com.sifionsolution.codex.enums.Role.LOGGED_OFF;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

import com.sifionsolution.codex.model.dao.UserDAO;
import com.sifionsolution.codex.model.dto.SignUpUser;
import com.sifionsolution.codex.security.AllowTo;
import com.sifionsolution.codex.security.encryption.PasswordEncryption;
import com.sifionsolution.codex.security.validation.LoginAvailable;

@Controller
@AllowTo(LOGGED_OFF)
public class RegisterController {

	@Inject
	private Result result;

	@Inject
	private Validator validator;

	@Inject
	private UserDAO dao;

	@Get("/sign/up")
	public void index() {
	}

	@Post("/registrer")
	public void register(@NotNull @Valid @LoginAvailable SignUpUser user,
			@NotNull(message = "{empty.password}") @PasswordEncryption String password) {
		validator.onErrorRedirectTo(RegisterController.class).index();

		dao.register(user, password);

		result.redirectTo(RootController.class).index();
	}

}
