package com.websiteshop.AdminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.entity.Account;
import com.websiteshop.model.AccountDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.OrderDetailService;
import com.websiteshop.service.OrderService;
import com.websiteshop.service.StorageService;

@Controller
@RequestMapping("admin/accounts")
public class AccountAdminController {
	@Autowired
	AccountService accountService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	StorageService storageService;

	@Autowired
	JavaMailSender javaMailSender;

	@RequestMapping("")
	public String list(Model model) {
		List<Account> list = accountService.findAll();
		model.addAttribute("accounts", list);
		return "admin/accounts/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		AccountDto dto = new AccountDto();
		dto.setIsEdit(false);
		model.addAttribute("account", dto);
		return "admin/accounts/addOrEdit";
	}

	@GetMapping("edit/{username}")
	public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {

		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();

		if (opt.isPresent()) {
			Account entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("account", dto);
			return new ModelAndView("admin/accounts/editAccount", model);
		}

		model.addAttribute("message", "Tài khoản không tồn tại");

		return new ModelAndView("forward:/admin/accounts", model);
	}

	@GetMapping("reset")
	public String createAccount(Model model) {
		AccountDto p = new AccountDto();
		model.addAttribute("account", p);

		return "admin/accounts/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@ModelAttribute("account") AccountDto dto, BindingResult result) {

		try {
			if (result.hasErrors()) {
				return new ModelAndView("admin/accounts/addOrEdit");
			}
			Account entity = new Account();
			BeanUtils.copyProperties(dto, entity);

			if (!dto.getImageFile().isEmpty()) {
				entity.setImage(storageService.getStoredFilename(dto.getImageFile(), null));
				storageService.store(dto.getImageFile(), entity.getImage());
			}

			accountService.save(entity);
			model.addAttribute("message", "Tài khoản đã được lưu");
			return new ModelAndView("forward:/admin/accounts", model);
		} catch (Exception e) {
			model.addAttribute("message", "Kiểm tra lại các trường dữ liệu");
			return new ModelAndView("admin/accounts/addOrEdit", model);
		}
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete/{username}")
	public ModelAndView delete(ModelMap model, @PathVariable("username") String username) throws IOException {
		Optional<Account> opt = accountService.findById(username);

		try {
			if (opt.isPresent()) {
				if (!StringUtils.isEmpty(opt.get().getImage())) {
					storageService.delete(opt.get().getImage());
				}
				// orderService.deleteOrderByUsername(username);
				accountService.delete(opt.get());
				model.addAttribute("message", "Tài khoản đã được xóa!");
			} else {
				model.addAttribute("message", "Không tìm thấy tài khoản!");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Không thể xóa tài khoản khi đã mua hàng!");
		}

		return new ModelAndView("forward:/admin/accounts", model);
	}

	@GetMapping("SendEmail")
	public String index() {
		return "/admin/accounts/SendMail";
	}

	@PostMapping("send-mail")
	public String send(Model model,
			@RequestParam("to") String to,
			@RequestParam("subject") String subject,
			@RequestParam("content") String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(content);

		javaMailSender.send(msg);
		model.addAttribute("message", "Đã gửi email thành công!");
		return "/admin/accounts/SendMail";

	}

	@GetMapping("/info/{username}")
	public String info(Model model, @PathVariable("username") String username) {
		Account acc = accountService.findById(username).get();
		model.addAttribute("info", acc);
		return "admin/accounts/info";
	}

	@GetMapping("/info/edit/{username}")
	public String editInfo(ModelMap model, @PathVariable("username") String username) {

		Optional<Account> opt = accountService.findById(username);
		AccountDto dto = new AccountDto();

		if (opt.isPresent()) {
			Account acc = opt.get();
			BeanUtils.copyProperties(acc, dto);

			model.addAttribute("account", dto);
			return "admin/accounts/edit";
		}

		model.addAttribute("message", "Lỗi thiết lập tài khoản!");

		return "admin/accounts/edit";
	}

	@PostMapping("/info/saveOrUpdate")
	public String saveOrUpdateInfo(ModelMap model,
			@ModelAttribute("account") AccountDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return "admin/accounts/edit";
		}
		Account acc = new Account();
		BeanUtils.copyProperties(dto, acc);

		if (!dto.getImageFile().isEmpty()) {
			acc.setImage(storageService.getStoredFilename(dto.getImageFile(), null));
			storageService.store(dto.getImageFile(), acc.getImage());
		}

		accountService.save(acc);
		model.addAttribute("message", "Lưu thành công!");
		return "admin/accounts/edit";
	}

	@GetMapping("/info/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> saveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
