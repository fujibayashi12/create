package com.example.moattravel.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.moattravel.entity.User;
import com.example.moattravel.service.UserService;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsersController {

	private final UserService userService;
	
	public AdminUsersController(UserService userService) {
		this.userService = userService;
	}
	
	// ✅ ユーザー一覧ページを表示
    @GetMapping("")
    public String showUsersList(Pageable pageable, Model model) {
        Page<User> usersPage = userService.getAllUsers(pageable);
        model.addAttribute("usersList", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "admin/usersList"; 
    }

    // ✅ ユーザー検索機能
    @GetMapping("/search")
    public String searchUsers(@RequestParam(required = false) String keyword, Pageable pageable, Model model) {
        Page<User> usersPage = userService.searchUsers(keyword, pageable);
        model.addAttribute("usersList", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "admin/usersList";
    }

    // ✅ アカウント凍結機能
    @PostMapping("/freeze")
    public String freezeUser(@RequestParam("id") Integer userId, RedirectAttributes redirectAttributes) {
        userService.freezeUser(userId);
        redirectAttributes.addFlashAttribute("successMessage", "ユーザー ID: " + userId + " を凍結しました！");
        return "redirect:/admin/users";
    }
    
    //ユーザーの詳細ページを開くメソッド
    @GetMapping("/{id}")
    public String showUserDetail(@PathVariable("id") Integer userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "admin/userDetail"; 
    }
    
    //ユーザー情報を管理側で変更するメソッド
    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Integer userId, 
                             @RequestParam("name") String name, 
                             @RequestParam("email") String email, 
                             @RequestParam("phoneNumber") String phoneNumber, 
                             RedirectAttributes redirectAttributes) {
        userService.updateUser(userId, name, email, phoneNumber);
        redirectAttributes.addFlashAttribute("successMessage", "ユーザー情報を更新しました！");
        return "redirect:/admin/users/" + userId;
    }

}

