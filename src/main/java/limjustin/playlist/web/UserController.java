package limjustin.playlist.web;

import limjustin.playlist.domain.user.Role;
import limjustin.playlist.dto.user.UserFormDto;
import limjustin.playlist.dto.user.UserLoginDto;
import limjustin.playlist.dto.user.UserSaveDto;
import limjustin.playlist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("formDto", new UserFormDto());
        return "user/createUser";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("formDto") @Valid UserFormDto formDto, BindingResult result) {

        if (result.hasErrors()) {
            return "user/createUser";
        }

        UserSaveDto user = new UserSaveDto();

        user.setName(formDto.getName());
        user.setPassword(formDto.getPassword());
        user.setRole(Role.GUEST);

        userService.join(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginUserForm(Model model) {
        model.addAttribute("loginDto", new UserLoginDto());
        return "user/loginUser";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("loginDto") @Valid UserLoginDto loginDto, BindingResult result) {

        if (result.hasErrors()) {
            return "user/loginUser";
        }

        Long userId = userService.check(loginDto.getName(), loginDto.getPassword());

        if (userId == 0L) {  // 로그인 실패
            return "redirect:/";
        }

        return "redirect:/main";  // 로그인 성공
    }
}
