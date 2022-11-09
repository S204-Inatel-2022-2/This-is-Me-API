package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.dtos.requests.UserCreatingAccountRequestDTO;
import br.inatel.thisismeapi.services.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccountAdmin(@RequestBody UserCreatingAccountRequestDTO userCreatingAccountRequestDTO) {

        LOGGER.info("m=createNewAccountAdmin, email={}", userCreatingAccountRequestDTO.getEmail());

        this.adminService.saveNewAccount(
                userCreatingAccountRequestDTO.getEmail(),
                userCreatingAccountRequestDTO.getPassword(),
                userCreatingAccountRequestDTO.getVerifyPassword(),
                userCreatingAccountRequestDTO.getCharacterName()
        );
        LOGGER.info("m=createNewAccountAdmin, status=CREATED");
    }

    @DeleteMapping("/delete-user-by-email")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUserByEmail(@RequestParam String email) {
        LOGGER.info("m=deleteUserByEmail, email={}", email);
        this.adminService.deleteUserByEmail(email);
    }

    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        LOGGER.info("m=helloAdmin");
        return "Hello Admin";
    }
}
