package com.packt.spring.jsfsecurity;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.*;
import javax.faces.event.*;
import javax.servlet.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
@ManagedBean(name="loginController")
@RequestScoped
public class LoginController implements PhaseListener {
	public String doLogin() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().
			getExternalContext();
		RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
				.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward((ServletRequest) context.getRequest(),
			(ServletResponse) context.getResponse());
		FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	public void afterPhase(PhaseEvent event) {	
	}
	public void beforePhase(PhaseEvent event) {
		Exception e = (Exception) FacesContext.getCurrentInstance().
			getExternalContext().getSessionMap().get(
				WebAttributes.AUTHENTICATION_EXCEPTION);
         if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext().
				getSessionMap().put(WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Input not valid.", "Input not valid"));
        }
	}
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}