package com.misiontic2022.apichiquitines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.misiontic2022.apichiquitines.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
 
}
