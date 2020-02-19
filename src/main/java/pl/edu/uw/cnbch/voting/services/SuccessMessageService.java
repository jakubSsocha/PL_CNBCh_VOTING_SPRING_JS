package pl.edu.uw.cnbch.voting.services;

import org.springframework.ui.Model;

public interface SuccessMessageService {

    Model addMessageTo(Model model, String message);
}
