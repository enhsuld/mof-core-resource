package com.macro.dev.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * The persistent class for the TC_EXCHANGE database table.
 * 
 */

@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TcExchange implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String last_date;
	private String rate;
	private String rate_float;



}