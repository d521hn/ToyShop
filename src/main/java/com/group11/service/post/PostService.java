package com.group11.service.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group11.dto.post.PostFormForCreating;
import com.group11.dto.post.PostFormForUpdating;
import com.group11.entity.Post;
import com.group11.repository.IPostRepository;
import com.group11.specification.post.PostSpecificationBuilder;

@Service
public class PostService implements IPostService {
	@Autowired 
	private IPostRepository repository;
	
	@Override
	public Page<Post> getAllPosts(Pageable pageable, String search) {
		PostSpecificationBuilder specification = new PostSpecificationBuilder(search);

		return repository.findAll(specification.build(), pageable);
	}

	@Override
	public boolean isPostExistsById(short id) {
		return repository.existsById(id);
	}

	@Override
	public void createPost(PostFormForCreating form) {
		repository.save(form.toEntity());
	}

	@Override
	public Post getPostByID(short id) {
		return repository.findById(id).get();
	}

	@Override
	public void updatePost(short id, PostFormForUpdating form) {
		Post entity = repository.findById(id).get();
		entity.setTitle(form.getTitle());
		entity.setContent(form.getContent());
		entity.setCreatedTime(form.getCreatedTime());
		entity.setUserId(form.getUserId());
		entity.setTitle(form.getTitle());
		repository.save(entity);
	}

	@Transactional
	public void deletePosts(List<Short> ids) {
		repository.deleteByIdIn(ids);
	}
}
