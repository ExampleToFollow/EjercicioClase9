package com.example.ejercicio6.Controller;

import com.example.ejercicio6.Entity.Character;
import com.example.ejercicio6.Repository.CharacterRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class WSController {
    //Crear rest server
    final CharacterRepository characterRepository;

    public WSController(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }
   @Getter
   @Setter
    public class Error{
        private String error;
        private LocalDateTime  date ;
    }
    @GetMapping(value = "/ws/personaje/list")
    public ResponseEntity<HashMap<String, Object>> listCharacters (@RequestParam(value = "page",required = false,defaultValue ="1") Integer page ,
                                                                   @RequestParam(value = "sort_attr", required = false, defaultValue="id") String sort_attr ,
                                                                   @RequestParam(value = "sort_type",required = false, defaultValue = "asc") String sort_type,
                                                                   @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                                                                   @RequestParam(value = "search_attr",required = false, defaultValue = "name") String search_attr,
                                                                   @RequestParam(value = "search_text",required = false, defaultValue = "") String search_text
    ){
        HashMap<String, Object> responseJson = new HashMap<>();
        HashMap<String, Object> pageable = new HashMap<>();
        //Definimos valores por defecto

        String error = limit>20  ? "error" : "";

        if(error.equals("")){
            List<Character> charac =  characterRepository.megaFiltrado(search_attr,'%'+search_text+'%',sort_attr,sort_type,limit,page*limit );
            responseJson.put("content", charac);
            responseJson.put("pageable", charac);
            return ResponseEntity.ok(responseJson);
        }else{
            HashMap<String, Object> err = new HashMap<>();
            err.put("error","limite invalido, debe ser menor a 20");
            err.put("date",""+LocalDate.now());
            return ResponseEntity.badRequest().body(err);
        }
    }
    //PARA QUE JOSH TRABAJE :


    @DeleteMapping(value = "/ws/personaje/delete/{id}")
    public ResponseEntity<HashMap<String,Object>> borrar(@PathVariable("id") String idStr){
        HashMap<String, Object> jsonResponse=new HashMap<>();
        try{
            Integer id=Integer.parseInt(idStr);
            Optional<Character> optionalCharacter = characterRepository.findById(id);
            if(optionalCharacter.isPresent()){
                characterRepository.deleteById(id);
                return ResponseEntity.ok(jsonResponse);
            }else {
                jsonResponse.put("error","ID personaje NO encontrado");
                String horaActual= LocalDateTime.now().toString();
                jsonResponse.put("date",horaActual);
                return ResponseEntity.badRequest().body(jsonResponse);
            }
        }catch (NumberFormatException e){
            jsonResponse.put("error","El ID no es un número");
            String horaActual=LocalDateTime.now().toString();
            jsonResponse.put("date",horaActual);
            return ResponseEntity.badRequest().body(jsonResponse);
        }
    }
    @PostMapping(value = "/ws/personaje/save")
    public ResponseEntity<HashMap<String,Object>> guardar(@RequestParam(value = "id",required = false)String idStr,
                                                          @RequestParam("name")String name,
                                                          @RequestParam("url")String url,
                                                          @RequestParam("identity")String identity,
                                                          @RequestParam("align")String align,
                                                          @RequestParam("eye")String eye,
                                                          @RequestParam("hair")String hair,
                                                          @RequestParam("sex")String sex,
                                                          @RequestParam("gsm")String gsm,
                                                          @RequestParam("alive")String alive,
                                                          @RequestParam("appearances")String appearancesStr,
                                                          @RequestParam("firstAppearance")String firstAppearance,
                                                          @RequestParam("year")String yearStr) {

        HashMap<String, Object> jsonResponse = new HashMap<>();
        try {
            if (idStr != null) {
                Integer id = Integer.parseInt(idStr);
                Optional<Character> optionalCharacter = characterRepository.findById(id);
                if (optionalCharacter.isPresent()) {
                    Integer appearances = Integer.parseInt(appearancesStr);
                    Integer year = Integer.parseInt(yearStr);
                    characterRepository.editarPersonaje(name, url, identity, align, eye, hair, sex, gsm, alive, appearances, firstAppearance, year, id);
                    Optional<Character> personajeActualizado = characterRepository.findById(id);
                    jsonResponse.put("objeto", personajeActualizado.get());
                    return ResponseEntity.ok(jsonResponse);
                } else {
                    Integer appearances = Integer.parseInt(appearancesStr);
                    Integer year = Integer.parseInt(yearStr);
                    characterRepository.crearPersonaje(name, url, identity, align, eye, hair, sex, gsm, alive, appearances, firstAppearance, year);
                    Character personajeCreado = characterRepository.ultimoPersonaje();
                    jsonResponse.put("objeto", personajeCreado);
                    return ResponseEntity.ok(jsonResponse);
                }
            } else {
                Integer appearances = Integer.parseInt(appearancesStr);
                Integer year = Integer.parseInt(yearStr);
                characterRepository.crearPersonaje(name, url, identity, align, eye, hair, sex, gsm, alive, appearances, firstAppearance, year);
                Character personajeCreado = characterRepository.ultimoPersonaje();
                jsonResponse.put("objeto", personajeCreado);
                return ResponseEntity.ok(jsonResponse);
            }
        } catch (NumberFormatException e) {
            jsonResponse.put("error", "Error en validación de datos");
            String horaActual = LocalDateTime.now().toString();
            jsonResponse.put("date", horaActual);
            return ResponseEntity.badRequest().body(jsonResponse);
        }
    }

}
