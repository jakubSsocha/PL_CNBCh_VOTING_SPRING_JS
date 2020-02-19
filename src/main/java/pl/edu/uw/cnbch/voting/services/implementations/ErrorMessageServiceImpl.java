package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.services.ErrorMessageService;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

    private final String MODEL_ATTRIBUTE_NAME = "message";
    private final String MESSAGE_TYPE = "error";

    @Override
    public Model addMessageTo(Model model, String message) {

        model.addAttribute(MODEL_ATTRIBUTE_NAME,
                new MessageDTO.Builder()
                        .text(message)
                        .type(MESSAGE_TYPE)
                        .build());
        return model;
    }


}
