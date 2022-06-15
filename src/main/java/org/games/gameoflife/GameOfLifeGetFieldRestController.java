package org.games.gameoflife;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/fields")
public class GameOfLifeGetFieldRestController {
    @GetMapping(path="/", produces = "application/json")
    public char[][] getFieldd() {
        return GameOfLife.getField();
    }

    @GetMapping(path="/msg", produces = "application/json")
    public String getMsgg() {
        return GameOfLife.getMsg();
    }

}
