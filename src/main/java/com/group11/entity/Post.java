package com.group11.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`Post`")
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private short id;
	
    @Column(name = "`title`", nullable = false, length = 100)
    private String title;
    
    @Column(name = "`content`", nullable = false, columnDefinition = "TEXT")
    private String content;
    
	@Column(name = "createdTime")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdTime;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;

	public Post(String title, String content, Date createdTime, short userId) {
		super();
		this.title = title;
		this.content = content;
		this.createdTime = createdTime;
		this.user = new User();
		this.user.setId(userId);
	}
	
	public void setUserId(short userId) {
		this.user = new User();
		this.user.setId(userId);
	}
	
}
