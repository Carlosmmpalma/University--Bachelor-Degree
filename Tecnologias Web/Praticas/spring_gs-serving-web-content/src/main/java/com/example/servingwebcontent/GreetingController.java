package com.example.servingwebcontent;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	/*@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}*/
        
        @PostMapping("/atendimento")
	public String atendimento(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model,
                @RequestParam(name="cidade", required=false, defaultValue="Paris") String cidade) {
		model.addAttribute("name", name);
                model.addAttribute("cidade", name);
                System.out.println("RECEBEMOS: "+name+cidade);
		return "resposta";  // nome do template usado para processar a vista devolvida
	}
        
        
    @GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
	                                     Model model, 
                                             HttpSession session) {
		model.addAttribute("name", name);
		System.out.println("SESSION "+session.getAttribute("x"));

               /* mas há outras variantes, com anotações @SessionAttribute */
            return "greeting";
}

}
