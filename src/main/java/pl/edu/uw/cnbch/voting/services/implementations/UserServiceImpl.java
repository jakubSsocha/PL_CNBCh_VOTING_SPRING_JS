package pl.edu.uw.cnbch.voting.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.uw.cnbch.voting.models.entities.Role;
import pl.edu.uw.cnbch.voting.models.entities.User;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserBasicDTO;
import pl.edu.uw.cnbch.voting.models.viewDTO.UserExtendedDTO;
import pl.edu.uw.cnbch.voting.repositories.RoleRepository;
import pl.edu.uw.cnbch.voting.repositories.UserRepository;
import pl.edu.uw.cnbch.voting.services.MainService;
import pl.edu.uw.cnbch.voting.services.UserService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MainService mainService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           MainService mainService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mainService = mainService;
        this.passwordEncoder = passwordEncoder;
    }

    //potrzebne do SpringSecurity
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        user.setUsername(user.getEmail());
        user.setCreatedDate(LocalDateTime.now());
        Role userRole = roleRepository.findByName("ROLE_USER");
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
        return userRepository.findAll();
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
        mainService.checkIfIsEmpty(user);
        return user.get();
    }
}
