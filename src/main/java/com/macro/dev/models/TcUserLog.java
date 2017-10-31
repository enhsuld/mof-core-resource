package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_USER_LOG database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcUserLog implements Serializable {
	private static final long serialVersionUID = 1L;


	private String login_dtm;
	private String logout_dtm;
	private String user_id;
	private String user_ip;
	private TcUser tcUser;

}