package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * The persistent class for the TC_FAQ database table.
 * 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcFaq implements Serializable {
	private static final long serialVersionUID = 1L;


	private String id;
	private String answer;
	private String question;
	private long rank;
}