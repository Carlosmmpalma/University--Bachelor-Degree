package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.Anunop;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao.UserDao;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Anuncio;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.User;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SpringSecurityController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private Anunop anunOp;

    @GetMapping("/")
    public String defaultPage(Model model) {
        return "index";
    }
    
    @GetMapping("/index")
    public String index(Model model) throws JsonProcessingException {
    String list_oferta = anunOp.getAnunsHome("oferta");
    String list_procura = anunOp.getAnunsHome("procura");

    model.addAttribute("list_oferta", list_oferta);
    model.addAttribute("list_procura", list_procura);

    return "index";
}

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Credenciais inválidas");
        }
        if (logout != null) {
            model.addAttribute("msg", "A sua sessão foi interrompida com sucesso");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model, HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }
    
     @GetMapping("/newuser")
    public String newuser(Model model) {
        List<String> currentUsers = userDao.getUsernameList();
        System.out.println("\n" + currentUsers.size() + " USERS: " + currentUsers.toString());
        
        return "newuser";
    }
    
      @PostMapping("/register")
       public String register(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String email1,
                       @RequestParam String email2,
                       Model model) {
                       
           if(email1.equals("")){
               model.addAttribute("error", "Por favor introduza um email válido");
                return "newuser";
           }

            // Check if email1.equals(email2)
            if (!email1.equals(email2)) {
                model.addAttribute("error", "Os endereços de email não são iguais!");
                return "newuser";
            }
            
            // Check if username already exists
            List<String> currentUsers = userDao.getUsernameList();
            for(int i=0;i<currentUsers.size();i++){
            if((currentUsers.get(i)).equals(username)){
            model.addAttribute("error", "Nome de utilizador já existe!");
            return "newuser";
            }
            }

            // User does not exist, encode the password and save the new user
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            User u = new User(username, encodedPassword, email1, "ROLE_USER");
            userDao.saveUser(u);
            model.addAttribute("success", "Conta criada com sucesso!");
            return "newuser";
        }
    
    @GetMapping("/anuncios")
    public String anuncios(Model model) {

       return "anuncios";
    }
    
        @GetMapping("/anuncios_user")
    public String anuncios_user(Model model) {

       return "anuncios_user";
    }
    
    @PostMapping("/anuncios_sub")
    public String anuncios_sub(@RequestParam String tipo,@RequestParam String zona, @RequestParam String anunciante, Model model) throws JsonProcessingException {
        String anuncios=anunOp.getAnuns(tipo, zona, anunciante, "ativo");
        model.addAttribute("lista_anuncios",anuncios);

       return "anuncios_sub";
    }
    
    @PostMapping("/anuncios_sub_user")
    public String anuncios_sub_user(@RequestParam String tipo,@RequestParam String zona, @RequestParam String anunciante, Model model,Authentication authentication) throws JsonProcessingException {
        String anuncios=anunOp.getAnuns(tipo, zona, anunciante,"ativo");
        model.addAttribute("lista_anuncios",anuncios);

       return "anuncios_sub_user";
    }
    
    @GetMapping("/user_interface")
    public String user_interface( Model model) throws JsonProcessingException {
    String list_oferta = anunOp.getAnunsHome("oferta");
    String list_procura = anunOp.getAnunsHome("procura");

    model.addAttribute("list_oferta", list_oferta);
    model.addAttribute("list_procura", list_procura);
        
       return "user_interface";
    }
    
    @GetMapping("/criar_anuncio")
    public String criar_anuncio(Model model){
        
       return "criar_anuncio";
    }
    
    @PostMapping("/regist_anun")
    public String regist_anun(@RequestParam  String tipo,@RequestParam String estado,@RequestParam String tipo_alojamento,
                                @RequestParam String genero,@RequestParam int preco, @RequestParam String detalhes,@RequestParam  String user_name,
                                @RequestParam int contacto,@RequestParam  String zona,Model model) {
        
        Anuncio a= new Anuncio(tipo,estado,tipo_alojamento,genero,preco,detalhes,user_name,contacto,zona);
        anunOp.saveAnuncio(a);
        
       return "regist_anun";
    }
    
    @GetMapping("/admin_interface")
    public String admin_interface(Model model){

        
       return "admin_interface";
    }
    
    @GetMapping("/gestao_anuncios")
        public String gestao_anuncios(Model model) throws JsonProcessingException {
        String list_ativos=anunOp.getAnunsperState("ativo");
        String list_inativos=anunOp.getAnunsperState("inativo");
        
        model.addAttribute("list_ativos", list_ativos);
        model.addAttribute("list_inativos", list_inativos);

            return "gestao_anuncios";
        }

    @PostMapping("/rgs_msg")
    public String rgs_msg(@RequestParam String user_name,@RequestParam int aid, @RequestParam String nome, @RequestParam String mensagem, Model model){
        anunOp.saveMensagem(aid, nome, mensagem, user_name);
    return "rgs_msg";
    }
    
    @PostMapping("/own_msgs")
    public String own_msgs(@RequestParam int aid,@RequestParam String user_name, Model model) throws JsonProcessingException {
        String list_msgs=anunOp.getMensagens(aid, user_name);
        model.addAttribute("list_msgs",list_msgs);
        
       return "own_msgs";
    }
    
    @PostMapping("/anuncio_alterado")
    public String anuncio_alterado(@RequestParam int aid,@RequestParam String estado, Model model) throws JsonProcessingException {
        anunOp.changeEstado(aid, estado);
        model.addAttribute("tex",estado);
        
       return "anuncio_alterado";
    }
    
    


}
