package com.neosofttech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="OrderCancelkReasons")
public class CancelOrderReasons 
{
	@Id
	@GeneratedValue
	private int id;
	
	//yaha reasons aaega 
	private String reason;
	//jarurat nahi rahi
	//maza nai raha pehle jaisa
	//delivery late hai
	//bore ho raha tha isliye order kia tha
	//dusra mila
	//dusri jagah sasta mila mujhe
	// nai batana hai
}
