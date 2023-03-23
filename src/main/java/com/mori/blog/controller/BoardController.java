package com.mori.blog.controller;

import com.mori.blog.model.board;
import com.mori.blog.repository.BoardRepository;
//import com.mori.blog.validator.BoardValidator;

import com.mori.blog.validator.BoardValidator;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable){

        Page<board> boards = boardRepository.findAll(pageable);
        int startpage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endpage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startpage", startpage);
        model.addAttribute("endpage", endpage);
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
//    public String form(@ModelAttribute board board, Model model)
//    {
//        boardRepository.save(board);
//        model.addAttribute(board);
//        return "redirect:/board/list";
//    }

    @PostMapping("/form")
    public String form(@Valid board board, BindingResult bindingResult)
    {
        boardValidator.validate(board, bindingResult);

        if(bindingResult.hasErrors()){
            return "/board/form";
        }

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
