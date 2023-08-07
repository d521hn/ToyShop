package com.group11.dto.post;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormForUpdating {
	String title;
	String content;
	Date createdTime;
	short userId;
}
