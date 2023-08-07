package com.group11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group11.dto.post.PostFormForCreating;
import com.group11.dto.post.PostFormForUpdating;
import com.group11.entity.Post;
import com.group11.service.post.IPostService;

@RestController
@RequestMapping(value = "api/v1/posts")
public class PostController {
	@Autowired
	private IPostService service;

	@GetMapping()
	public ResponseEntity<?> getAllPosts(
			Pageable pageable,
			@RequestParam(required = false)
			String search) {
		Page<Post> entities = service.getAllPosts(pageable, search);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> existsPostById(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.isPostExistsById(id), HttpStatus.OK);
	}


	@PostMapping()
	public ResponseEntity<?> createPost(@RequestBody PostFormForCreating form) {
		service.createPost(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getPostByID(@PathVariable(name = "id") short id) {
		return new ResponseEntity<>(service.getPostByID(id), HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updatePost(@PathVariable(name = "id") short id, @RequestBody PostFormForUpdating form) {
		service.updatePost(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{ids}")
	public ResponseEntity<?> deletePosts(@PathVariable(name = "ids") List<Short> ids) {
		service.deletePosts(ids);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}
}
