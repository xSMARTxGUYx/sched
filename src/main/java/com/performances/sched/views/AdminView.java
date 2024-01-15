package com.performances.sched.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Admin")
@Route("/admin")
public class AdminView extends VerticalLayout {
    
    public AdminView() {
        add(new H1("Hello Admin!!"));
    }
}
