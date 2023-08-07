package com.group11.dto.post;

import java.util.Date;

import com.group11.entity.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormForCreating {
	String title;
	String content;
	Date createdTime;
	short userId;
	
	public Post toEntity() {
		return new Post(title, content, createdTime, userId);
	}
}
