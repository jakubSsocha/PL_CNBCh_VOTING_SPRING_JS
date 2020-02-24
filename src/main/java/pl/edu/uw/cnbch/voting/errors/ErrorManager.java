package pl.edu.uw.cnbch.voting.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.edu.uw.cnbch.voting.errors.types.*;
import pl.edu.uw.cnbch.voting.services.ErrorMessageService;

@ControllerAdvice
public class ErrorManager {

    private final String DATA_INTEGRITY_EXCEPTION_MESSAGE =
            "Użytkownik o podanym adresie e-mail jest już zarejestrowany w bazie danych";
    private final String MESSAGE_PAGE_HANDLER_ADDRESS = "index.jsp";

    private ErrorMessageService errorMessageService;

    @Autowired
    public ErrorManager(ErrorMessageService errorMessageService) {
        this.errorMessageService = errorMessageService;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String AccessDeniedExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                AccessDeniedException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(LoadFromDatabaseException.class)
    public String DatabaseLoadExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                LoadFromDatabaseException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(InactiveResultException.class)
    public String InactiveResultExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                InactiveResultException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(ResultAlreadyExistsException.class)
    public String ResultDoesntExistExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                ResultAlreadyExistsException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(EmptyVoiceException.class)
    public String EmptyVoiceExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                EmptyVoiceException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(UnsuccessfulValidationException.class)
    public String UnsuccessfulValidationExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                UnsuccessfulValidationException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(PasswordsNotEqualException.class)
    public String PasswordNotEqualExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                PasswordsNotEqualException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public String IncorrectPasswordExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                IncorrectPasswordException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(VotingClosedException.class)
    public String VotingClosedExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                VotingClosedException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(VotingInactiveException.class)
    public String VotingInactiveExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                VotingInactiveException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(VotingNameNotUniqueException.class)
    public String VotingNameNotUniqueExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                VotingNameNotUniqueException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(AdminDeleteException.class)
    public String AdminDeleteExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                AdminDeleteException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(NoSystemAdminException.class)
    public String NoSystemAdminExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                NoSystemAdminException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(UserNoRoleException.class)
    public String UserNoRoleExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                UserNoRoleException.ErrorMessage);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String DataIntegrityViolationExceptionHandler(Model model){
        errorMessageService.addMessageTo(model,
                DATA_INTEGRITY_EXCEPTION_MESSAGE);
        return MESSAGE_PAGE_HANDLER_ADDRESS;
    }

}
