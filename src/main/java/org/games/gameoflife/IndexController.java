package org.games.gameoflife;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(@RequestParam(value="success",required = false,defaultValue = "false")
                        boolean success, Model model){
        model.addAttribute("loginSuccess",success);
        return "index";
    }
}
