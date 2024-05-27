package com.example.ejercicio6.Controller;

import com.example.ejercicio6.Entity.Character;
import com.example.ejercicio6.Repository.CharacterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
public class WSController {
    //Crear rest server
    final CharacterRepository characterRepository;

    public WSController(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    @GetMapping(value = "/ws/personaje/list")
    public List<Character> listCharacters (@RequestParam("page") int page ,
                                           @RequestParam("sort_attr") String sort_attr ,
                                           @RequestParam("sort_type") String sort_attr,
                                           @RequestParam("sort_attr") String sort_attr,



    ){
        HashSet<Character> primerFiltro  = new HashSet<>();
        primerFiltro= characterRepository.findByNameIgnoreCaseContaining('%'+busqueda+'%');
        HashSet<Character> segundoFiltro  = new HashSet<>();
        HashSet<Character> tercerFiltro  = new HashSet<>();
        HashSet<Character> cuartoFiltro  = new HashSet<>();
        HashSet<Character> quintoFiltro  = new HashSet<>();
        HashSet<Character> sextoFiltro  = new HashSet<>();


        return characterRepository.findAll();
    }
    //PARA QUE JOSH TRABAJE :

}
