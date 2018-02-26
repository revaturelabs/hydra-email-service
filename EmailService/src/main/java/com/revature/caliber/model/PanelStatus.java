package com.revature.caliber.model;

import java.io.Serializable;

/**
 * Designates if the associate passed/failed the panel
 * or passed/failed a particular topic.
 * @author Patrick Walsh
 *
 */
public enum PanelStatus implements Serializable{
		Pass,
		Repanel
}
