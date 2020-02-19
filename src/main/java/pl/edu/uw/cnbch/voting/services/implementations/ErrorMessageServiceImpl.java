package pl.edu.uw.cnbch.voting.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;
import pl.edu.uw.cnbch.voting.services.ErrorMessageService;
import pl.edu.uw.cnbch.voting.services.MainService;

@Service
public class ErrorMessageServiceImpl implements ErrorMessageService {

    private final String MODEL_ATTRIBUTE_NAME = "message";
    private final String MESSAGE_TYPE = "error";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MainService mainService;

    @Autowired
    public ErrorMessageServiceImpl(MainService mainService) {
        this.mainService = mainService;
    }

    @Override
    public Model addMessageTo(Model model, String message){
        saveMessageIntoLog(message);
        model.addAttribute(MODEL_ATTRIBUTE_NAME,
                new MessageDTO.Builder()
                        .text(message)
                        .type(MESSAGE_TYPE)
                        .build());
        return model;
    }

    private void saveMessageIntoLog(String message){
        String errorMessage = new StringBuilder()
                .append("Error message: ")
                .append(message)
                .toString();
        logger.error(errorMessage);
    }


}
