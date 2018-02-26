package com.revature.caliber.model;

import java.io.Serializable;

/**
 * System access roles. 
 * 
 * *****Listed in order of precedence descending******
 * 
 * @author Patrick Walsh
 * @author Stanley Chouloute
 * @author Adam Baker
 *
 */
public enum TrainerRole implements Serializable {
	ROLE_VP,
	ROLE_PANEL,
	ROLE_QC,
	ROLE_TRAINER,
	ROLE_STAGING,
	ROLE_INACTIVE

}
