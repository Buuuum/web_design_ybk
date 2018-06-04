package ims.sunmoon.presantation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ims.sunmoon.domain.LoginInfo;
import ims.sunmoon.service.login.LoginService;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginGet(LoginInfo loginInfo, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);

		Boolean isLogin = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
		if (isLogin) {
			return new ModelAndView(new RedirectView("/bd/list"));
		} else {
			return new ModelAndView("/login");
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request) throws Exception {
		LoginInfo loginInfo = this.loginService.login(request);

		if (loginInfo == null) {
			request.getSession().setAttribute("isLogin", false);
		} else {
			request.getSession().setAttribute("isLogin", true);
		}

		return new ModelAndView(new RedirectView("/bd/list"));
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(false);

		if (session != null) {
			session.invalidate();
		}

		return new ModelAndView(new RedirectView("/login"));
	}
}
