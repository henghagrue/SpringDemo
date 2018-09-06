package com.example.oauth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.example.oauth.model.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 自定义登录失败页面跳转
 * @author upbchain12
 *
 */
@Component
public class MyAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;
	/**
	 * Calls the parent class {@code handle()} method to forward or redirect to the target
	 * URL, and then calls {@code clearAuthenticationAttributes()} to remove any leftover
	 * session data.
	 */ 
	@Override
	/**
	 * Performs the redirect or forward to the {@code defaultFailureUrl} if set, otherwise
	 * returns a 401 error code.
	 * <p>
	 * If redirecting or forwarding, {@code saveException} will be called to cache the
	 * exception for use in the target view.
	 */
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		logger.info("登录失败");
		request.setAttribute("loginError", "用户名或密码不正确");
		request.getRequestDispatcher("/uaa/login").forward(request, response);//跳转页面
//		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse(500,exception.getMessage(),null)));
//		if (defaultFailureUrl == null) {
//			logger.debug("No failure URL set, sending 401 Unauthorized error");
//
//			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//					"Authentication Failed: " + exception.getMessage());
//		}
//		else {
//			saveException(request, exception);
//
//			if (forwardToDestination) {
//				logger.debug("Forwarding to " + defaultFailureUrl);
//
//				request.getRequestDispatcher(defaultFailureUrl)
//						.forward(request, response);
//			}
//			else {
//				logger.debug("Redirecting to " + defaultFailureUrl);
//				redirectStrategy.sendRedirect(request, response, defaultFailureUrl);
//			}
//		}
	}	
}
