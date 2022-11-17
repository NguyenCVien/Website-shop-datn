
//package main.java.com.websiteshop.rest.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.websiteshop.dao.AuthorityDAO;
//import com.websiteshop.entity.Authority;
//import com.websiteshop.service.AuthorityService;
//
//@RestController
//public class AuthoRestController {
//
//    @Autowired
//    AuthorityDAO AuthorityDAO;
//
//    @Autowired
//    RoleDAO RoleDAO;
//
//    @Autowired
//    AccountDAO AccountDAO;
//
//    @GetMapping("rest/authorities")
//    public Map<String, Object> getAuthorities() {
//        Map<String, Object> data = new HashMap<>();
//        data.put("authorities", AuthorityDAO.findAll());
//        data.put("roles", RoleDAO.findAll());
//        data.put("accounts", AccountDAO.findAll());
//        return data;
//    }
//
//}
