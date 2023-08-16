package com.group11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.group11.entity.Post;

public interface IPostRepository extends JpaRepository<Post, Short>, JpaSpecificationExecutor<Post>{
	public Post findByTitle(String title);

	public boolean existsByTitle(String title);


	public void deleteByIdIn(List<Short> ids);
}
