package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_USER_ROLE database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcUserRole implements Serializable {
	private static final long serialVersionUID = 1L;


	private String reg_dtm;
	private String reg_id;
	private String user_id;
	private String role_id;
	private TcRole tcRole;
	private TcUser tcUser;
}