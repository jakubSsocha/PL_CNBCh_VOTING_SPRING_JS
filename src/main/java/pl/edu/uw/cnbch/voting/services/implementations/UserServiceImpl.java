package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.errors.types.AdminDeleteException;
import pl.edu.uw.cnbch.voting.errors.types.LoadFromDatabaseException;
import pl.edu.uw.cnbch.voting.errors.types.NoSystemAdminException;
import pl.edu.uw.cnbch.voting.errors.types.UserNoRoleException;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.RolesDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserBasicDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserExtendedDTO;
import pl.edu.uw.cnbch.voting.repositories.UserRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.ResultService;
import pl.edu.uw.cnbch.voting.services.RoleService;
import pl.edu.uw.cnbch.voting.services.UserService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ResultService resultService;
    private final MainService mainService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           ResultService resultService,
                           MainService mainService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.resultService = resultService;
        this.mainService = mainService;
        this.passwordEncoder = passwordEncoder;
    }

    //potrzebne do SpringSecurity
    @Override
    public User findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        if (user.getEnabled() == 0){
            return new User();
        }
        return user;
    }

    @Override
    public void saveInactiveUser(User user) throws Exception {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        user.setUsername(user.getEmail());
        user.setCreatedDate(LocalDateTime.now());
        user.setRoles(new HashSet<Role>(Arrays.asList(roleService.findByName("ROLE_USER"))));
        saveUserInDatabase(user);
    }

    @Override
    public void saveUserNewPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        saveUserInDatabase(user);
    }

    @Override
    public List<UserBasicDTO> findBasicInfoForAllUsers(){
        List<UserBasicDTO> userBasicDTOList = new ArrayList<>();
        for(User u : userRepository.findAllUsers()){
            userBasicDTOList.add(new UserBasicDTO(u));
        }
        return userBasicDTOList;
    }

    @Override
    public UserExtendedDTO findExtendedDataForUser(Long id) {
        return new UserExtendedDTO(findByUserId(id));
    }

    @Override
    public void deactivateUserWithId(Long id) {
        User user = findByUserId(id);
        checkIfUserIsAdmin(user);
        user.setEnabled(0);
        saveUserInDatabase(user);
        resultService.setAllUnclosedUserResultInactive(id);
    }

    private void checkIfUserIsAdmin(User user) {
        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_ADMIN")) {
                throw new AdminDeleteException();
            }
        }
    }

    @Override
    public void activateUserWithId(Long id){
        User user = findByUserId(id);
        user.setEnabled(1);
        saveUserInDatabase(user);
        resultService.setAllUnclosedUserResultActive(id);
    }

    @Override
    public void changeRoles(Long id, RolesDTO rolesDTO) throws Exception {
        List<User> allAdmins = getAllAdmins();
        User user = findByUserId(id);
        if(userHasLessThanOneRole(rolesDTO)){
            throw new UserNoRoleException();
        }
        if(userIsLastAdminInSystem(user,rolesDTO)){
            if(allAdmins.size() < 2){
                throw new NoSystemAdminException();
            }
        }
        user.setRoles(rolesDTO.getRoles());
        saveUserInDatabase(user);
    }

    private List<User> getAllAdmins() throws Exception{
        Role admin = roleService.findByName("ROLE_ADMIN");
        return userRepository.getAllAdmins(admin);
    }

    private boolean userHasLessThanOneRole(RolesDTO rolesDTO){
        if(rolesDTO.getRoles().size() <1){
            return true;
        }
        return false;
    }

    private boolean userIsLastAdminInSystem(User user, RolesDTO rolesDTO) throws Exception{
        Role admin = roleService.findByName("ROLE_ADMIN");
        if(user.getRoles().contains(admin) && !rolesDTO.getRoles().contains(admin)){
            return true;
        }
        return false;
    }

// repository methods

    private void saveUserInDatabase(User user){
        userRepository.save(user);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findUserByID(id).orElseThrow(
                () -> new LoadFromDatabaseException()
        );
    }

    @Override
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
    }


}
