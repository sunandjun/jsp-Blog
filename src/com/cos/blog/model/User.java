package com.cos.blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class User {
	private int id;  //넘버링 1,2,3,
	private String username; //유저이름
	private String password;
	private String email;
	private String address;
	private Timestamp createDate;

}
