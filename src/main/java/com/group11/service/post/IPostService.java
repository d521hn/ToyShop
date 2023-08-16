package com.group11.service.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.group11.dto.post.PostFormForCreating;
import com.group11.dto.post.PostFormForUpdating;
import com.group11.entity.Post;

public interface IPostService {
	Page<Post> getAllPosts(Pageable pageable, String search);
	
	boolean isPostExistsById(short id);
	
	boolean isPostExistsByTitle(String title);

	void createPost(PostFormForCreating form);

	Post getPostByID(short id);

	void updatePost(short id, PostFormForUpdating form);

	void deletePosts(List<Short> ids);
}
