package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_QUESTION database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String answer;
	private String question;
	private String reg_dtm;
}