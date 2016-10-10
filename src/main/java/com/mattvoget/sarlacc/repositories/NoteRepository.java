package com.mattvoget.sarlacc.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mattvoget.sarlacc.models.Note;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "note", path = "note")
public interface NoteRepository extends MongoRepository<Note, String>{

}
