package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_USER_AUDIT database table.
 * 
 */

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcUserAudit implements Serializable {
	private static final long serialVersionUID = 1L;


	private String user_id;
	private String act_cd;
	private String prg_id;
	private String req_dtm;
	private String req_user_ip;
	private TcUser tcUser;
}