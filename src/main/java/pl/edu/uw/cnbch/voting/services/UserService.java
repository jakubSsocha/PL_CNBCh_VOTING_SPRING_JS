package pl.edu.uw.cnbch.voting.services;

import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.RolesDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserBasicDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserExtendedDTO;

import java.util.List;

public interface UserService {

    User findByUserName(String name);

    void saveInactiveUser(User user) throws Exception;

    List<User> findAllActiveUsers();

    void saveUserNewPassword(User user);

    List<UserBasicDTO> findBasicInfoForAllUsers();

    UserExtendedDTO findExtendedDataForUser(Long id) throws Exception;

    User findByUserId(Long id) throws Exception;

    void deactivateUserWithId(Long id) throws Exception;

    void activateUserWithId(Long id) throws Exception;

    void changeRoles(Long id, RolesDTO rolesDTO) throws Exception;
}
