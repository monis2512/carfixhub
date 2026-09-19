package com.shahrukh.autocare;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/admin")
public class AdminController {
 private static final String KEY="ADMIN_AUTHENTICATED";
 private final CarQueryRepository repo;
 @Value("${app.admin.username}") private String username;
 @Value("${app.admin.password}") private String password;
 public AdminController(CarQueryRepository repo){this.repo=repo;}
 @PostMapping("/login") public ResponseEntity<?> login(@RequestBody Map<String,String> c,HttpSession s){
  if(username.equals(c.getOrDefault("username",""))&&password.equals(c.getOrDefault("password",""))){
   s.setAttribute(KEY,true); return ResponseEntity.ok(Map.of("success",true));
  }
  return ResponseEntity.status(401).body(Map.of("message","Invalid admin username or password."));
 }
 @PostMapping("/logout") public Map<String,Boolean> logout(HttpSession s){s.invalidate();return Map.of("success",true);}
 @GetMapping("/session") public Map<String,Boolean> session(HttpSession s){return Map.of("authenticated",Boolean.TRUE.equals(s.getAttribute(KEY)));}
 @GetMapping("/queries") public ResponseEntity<?> queries(HttpSession s){
  if(!auth(s)) return ResponseEntity.status(401).body(Map.of("message","Admin login required."));
  return ResponseEntity.ok(repo.findAllByOrderByCreatedAtDesc());
 }
 @DeleteMapping("/queries/{id}") public ResponseEntity<?> delete(@PathVariable Long id,HttpSession s){
  if(!auth(s)) return ResponseEntity.status(401).body(Map.of("message","Admin login required."));
  if(!repo.existsById(id)) return ResponseEntity.notFound().build();
  repo.deleteById(id); return ResponseEntity.ok(Map.of("success",true));
 }
 private boolean auth(HttpSession s){return Boolean.TRUE.equals(s.getAttribute(KEY));}
}
