package pl.edu.uw.cnbch.voting.services;

import org.springframework.ui.Model;

public interface ErrorMessageService {

    Model addMessageTo(Model model, String message);
}
