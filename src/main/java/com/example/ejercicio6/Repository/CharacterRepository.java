package com.example.ejercicio6.Repository;
import com.example.ejercicio6.Entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Integer> {
    @Query(nativeQuery = true,value = "select distinct(identity) from characters where identity is not null and identity != ' '")
    List<String> listarIdentity();

    @Query(nativeQuery = true,value = "select distinct(align) from characters where align is not null and align != ' '")
    List<String> listarAlineacion();

    @Query(nativeQuery = true,value = "select distinct(eye) from characters where eye is not null and eye != ' '")
    List<String> listarOjos();

    @Query(nativeQuery = true,value = "select distinct(hair) from characters where hair is not null and hair != ' '")
    List<String> listarCabello();

    @Query(nativeQuery = true,value = "select distinct(sex) from characters where sex is not null and sex != ' '")
    List<String> listarSexo();

    @Query(nativeQuery = true,value = "select distinct(gsm) from characters where gsm is not null and gsm != ' '")
    List<String> listarGSM();

    @Query(nativeQuery = true,value = "select distinct(alive) from characters where alive is not null and alive != ' '")
    List<String> listarAlive();

    @Query(nativeQuery = true, value = "select * from characters where name like ?1")
    List<Character> findByNameIgnoreCaseContaining(String busqueda);

    @Query(nativeQuery = true, value = "select * from characters where ?1 like ?2 order by ?3 ?4 LIMIT ?5 OFFSET ?6;")
    List<Character> megaFiltrado(String parametroFiltrado , String textoBusqueda , String parametroOrden , String criterioOrden , int limit, int offset);


}
