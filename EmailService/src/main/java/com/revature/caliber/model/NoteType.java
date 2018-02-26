package com.revature.caliber.model;

import java.io.Serializable;

/**
 * Determines if notes are created by Trainer or QC 
 * and where they are applied (to the trainee or batch).
 * Public notes are made by QC that are not visible to trainers and below.
 * 
 * @author Patrick Walsh
 *
 */
public enum NoteType implements Serializable{
	TRAINEE,
	BATCH,
	QC_TRAINEE,
	QC_BATCH
}
