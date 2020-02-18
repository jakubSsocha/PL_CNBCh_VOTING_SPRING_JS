package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
        Role userRole = roleService.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveUserNewPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAllActiveUsers() {
        return userRepository.findAllActiveUsers();
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
    public UserExtendedDTO findExtendedDataForUser(Long id) throws Exception {
        User user = findByUserId(id);
        return new UserExtendedDTO(user);
    }

    @Override
    public User findByUserId(Long id) throws Exception {
        Optional<User> user = userRepository.findUserByID(id);
        mainService.checkIfOptionalIsEmpty(user);
        return user.get();
    }

    @Override
    public void deactivateUserWithId(Long id) throws Exception{
        User user = findByUserId(id);
        checkIfUserIsAdmin(user);
        user.setEnabled(0);
        userRepository.save(user);
        resultService.setAllUnclosedUserResultInactive(id);
    }

    private void checkIfUserIsAdmin(User user) throws Exception {
        for (Role r : user.getRoles()) {
            if (r.getName().equals("ROLE_ADMIN")) {
                throw new Exception("Nie można usunąć użytkownika który jest Administratorem");
            }
        }
    }

    @Override
    public void activateUserWithId(Long id) throws Exception {
        User user = findByUserId(id);
        user.setEnabled(1);
        userRepository.save(user);
        resultService.setAllUnclosedUserResultActive(id);
    }

    @Override
    public void changeRoles(Long id, RolesDTO rolesDTO) throws Exception {
        List<User> allAdmins = getAllAdmins();
        User user = findByUserId(id);
        Role admin = roleService.findByName("ROLE_ADMIN");
        if(rolesDTO.getRoles().size() < 1){
            throw new Exception("Każdy użytkownik musi mieć przynajmniej jedną rolę");
        }
        if(user.getRoles().contains(admin) && !rolesDTO.getRoles().contains(admin)){
            if(allAdmins.size() < 2){
                throw new Exception("Przynajmniej jeden Administrator musi być aktywny");
            }
        }
        user.setRoles(rolesDTO.getRoles());
        userRepository.save(user);
    }

    private List<User> getAllAdmins() throws Exception{
        Role admin = roleService.findByName("ROLE_ADMIN");
        return userRepository.getAllAdmins(admin);
    }
}
