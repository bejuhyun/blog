package com.mori.blog.controller;

import com.mori.blog.model.board;
import com.mori.blog.repository.BoardRepository;
//import com.mori.blog.validator.BoardValidator;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;



    @GetMapping("/list")
    public String list(Model model){
        List<board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id)
    {
        if (id == null){
            model.addAttribute("board", new board());
        } else {
            board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

//    @PostMapping("/form")
//    public String form(@Valid board board, BindingResult bindingResult)
//    {
////        boardValidator.validate(board, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "board/form";
//        }
//        boardRepository.save(board);
//        return "redirect:/board/list";
//    }
    @PostMapping("/form")
    public String form(@ModelAttribute board board, Model model)
    {
        boardRepository.save(board);
        model.addAttribute(board);
        return "redirect:/board/list";
    }
}
