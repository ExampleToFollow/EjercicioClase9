package com.example.ejercicio6.Controller;

import com.example.ejercicio6.Entity.Character;
import com.example.ejercicio6.Repository.CharacterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ws")
public class WSController {
    //Crear rest server
    final CharacterRepository characterRepository;

    public WSController(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }
    @ResponseBody
    @GetMapping(value = "/ws/personaje/list")
    public List<Character> listCharacters (
                                           @RequestParam(value = "busqueda",required = false) String busqueda){
        if(busqueda==null){
            return characterRepository.findAll();
        }else{
            return  characterRepository.findByNameIgnoreCaseContaining('%'+busqueda+'%');
        }
    }

}
