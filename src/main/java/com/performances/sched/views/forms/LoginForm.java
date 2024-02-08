package com.performances.sched.views.forms;


import com.performances.sched.service.LoginService;
import com.performances.sched.views.AdminView;
import com.performances.sched.views.CustomerView;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

@CssImport("./my-styles/styles.css")
public class LoginForm extends FormLayout {

    // Intantiate the text field variables
    TextField userName = new TextField ("Username:");
    PasswordField password = new PasswordField("Password:");
    
    // Initiate the Buttons for loging in or signing up 
    Button loginButton = new Button("Login");
    Button signupButton = new Button("Sign Up");

    LoginService login;

    public LoginForm(LoginService login) {
        this.login = login;

        //Add a Class name for CSS editing
        addClassName("login-form");

        // Add the components to the Form
        add(userName,
        password,
        buttons());
    }

    private HorizontalLayout buttons() {
        // Configure the Buttons to be next to each other
        // Configure the themes of the Buttons
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signupButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        // Sign Up form access
        signupButton.addClickListener(event -> {
            // Close parent Dialog
            getUI().ifPresent(ui -> ((Dialog) getParent().get()).close());
            // Open Sign Up Dialog
            Dialog dialog = new Dialog();
            dialog.addClassName("dialog-form");
            SignUpForm signUp = new SignUpForm();
            dialog.add(signUp);
            dialog.open();    
        });
        // Add shortcut key function to Buttons
        loginButton.addClickShortcut(Key.ENTER);

        // Add the Navigation to the login button
        loginButton.addClickListener(this::loginSuccess);

        // Return the buttons
        return new HorizontalLayout(loginButton, signupButton);
    }
    
    public void loginSuccess(ClickEvent<Button> event) {
        String user = userName.getValue();
        String pwd = password.getValue();

        if (login == null) {
            Notification.show("Login Unavailable");
            return;
        }
        
        String validLogin = login.login(user, pwd);

        if ("admin".equals(validLogin)){
            getUI().ifPresent(ui -> ui.navigate(AdminView.class));
        }else if ("customer".equals(validLogin)){
            getUI().ifPresent(ui -> ui.navigate(CustomerView.class));
        }else {
            Notification.show("Invalid Login Credentials \nPlease try again.");
        }
    }

}
