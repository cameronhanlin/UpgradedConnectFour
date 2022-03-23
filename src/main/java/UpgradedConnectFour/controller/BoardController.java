package UpgradedConnectFour.controller;

import UpgradedConnectFour.data.BoardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    private BoardRepository boardRepository = new BoardRepository();

    @RequestMapping("/")
    public String displayHome(ModelMap modelMap){
        modelMap.put("String", "Test2");
        return "home";
    }


}
