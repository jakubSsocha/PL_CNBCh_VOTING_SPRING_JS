package pl.edu.uw.cnbch.voting.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.uw.cnbch.voting.errors.types.AccessDeniedException;
import pl.edu.uw.cnbch.voting.errors.types.LoadFromDatabaseException;
import pl.edu.uw.cnbch.voting.errors.types.InactiveResultException;
import pl.edu.uw.cnbch.voting.errors.types.ResultAlreadyExistException;
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

    @ExceptionHandler(ResultAlreadyExistException.class)
    public String ResultDoesntExistExceptionHandler(Model model){
        model.addAttribute("message", MessageDTO.generateMessage(
                ResultAlreadyExistException.ErrorMessage,
                "error"
        ));
        return "index.jsp";
    }


}
