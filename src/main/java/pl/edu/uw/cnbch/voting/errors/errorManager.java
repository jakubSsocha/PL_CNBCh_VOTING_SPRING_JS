package pl.edu.uw.cnbch.voting.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.uw.cnbch.voting.errors.types.*;
import pl.edu.uw.cnbch.voting.models.viewDTO.MessageDTO;

@ControllerAdvice
public class errorManager {

    @ExceptionHandler(AccessDeniedException.class)
    public String AccessDeniedExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                AccessDeniedException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }

    @ExceptionHandler(LoadFromDatabaseException.class)
    public String DatabaseLoadExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                LoadFromDatabaseException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }

    @ExceptionHandler(InactiveResultException.class)
    public String InactiveResultExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                InactiveResultException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }

    @ExceptionHandler(ResultAlreadyExistsException.class)
    public String ResultDoesntExistExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                ResultAlreadyExistsException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }

    @ExceptionHandler(EmptyVoiceException.class)
    public String EmptyVoiceExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                EmptyVoiceException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }


}
