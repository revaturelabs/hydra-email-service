package com.revature.caliberemailservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonObject;
import com.revature.caliber.CaliberTest;
//import com.revature.caliber.data.TrainerDAO;
import com.revature.caliber.email.Mailer;
import com.revature.caliber.model.Trainer;

/**
 * Tests that the correct trainers are selected for emailing.
 * 
 * @author Will Underwood
 *
 */
public class MailerTest extends CaliberTest {

	
	@Autowired
	AmqpTemplate rabbitMqTemplate;
	
	private Mailer mailer;
	private Set<Trainer> trainersToSubmitGrades;
//	private TrainerDAO trainerDAO;
	private Trainer gray;
	private Trainer patrick;
	private Trainer dan;
	private Trainer orson;
	private Trainer shelby;
	private Trainer walter;
	private Trainer natalie;
	private Trainer archer;
	
	@Autowired
	public void setMailer(Mailer mailer) {
		this.mailer = mailer;
	}

//	@Autowired
//	public void setTrainerDAO(TrainerDAO trainerDAO) {
//		this.trainerDAO = trainerDAO;
//	}

	/**
	 * Finds trainers who need to submit grades
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.trainersToSubmitGrades = this.mailer.getTrainersWhoNeedToSubmitGrades();
		JsonObject jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "grawyne@gmail.com");
		this.gray = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.gray = this.trainerDAO.findByEmail("grawyne@gmail.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "patrick.walsh@revature.com");
		this.patrick = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.patrick = this.trainerDAO.findByEmail("patrick.walsh@revature.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "pjw6193@hotmail.com");
		this.dan = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.dan = this.trainerDAO.findByEmail("pjw6193@hotmail.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "owallace@mailinator.com");
		this.orson = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.orson = this.trainerDAO.findByEmail("owallace@mailinator.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "slevinson@mailinator.com");
		this.shelby = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.shelby = this.trainerDAO.findByEmail("slevinson@mailinator.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "wpayne@mailinator.com");
		this.walter = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.walter = this.trainerDAO.findByEmail("wpayne@mailinator.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "nchurch@mailinator.com");
		this.natalie = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.natalie = this.trainerDAO.findByEmail("nchurch@mailinator.com");
		jobj = new JsonObject();
		jobj.addProperty("methodName", "findByEmail");
		jobj.addProperty("email", "aradcliff@mailinator.com");
		this.archer = (Trainer) rabbitMqTemplate.convertSendAndReceive("revature.caliber.repos", "trainerList", jobj);
//		this.archer = this.trainerDAO.findByEmail("aradcliff@mailinator.com");
	}

	/**
	 * Gray Wynne should be absent from the collection, because he submitted all
	 * his grades
	 */
	@Test
	public void testGrayAbsent() {
		assertFalse(this.trainersToSubmitGrades.contains(this.gray));
	}

	/**
	 * Patrick Walsh should be in the collection, because he has submitted only
	 * some grades
	 */
	@Test
	public void testPatrickPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.patrick));
	}

	/**
	 * Dan Pickles should be in the collection, because he has not submitted any
	 * grades
	 */
	@Test
	public void testDanPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.dan));
	}

	/**
	 * Orson Wallace should be in the collection, because he has submitted all
	 * but one grade
	 */
	@Test
	public void testOrsonPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.orson));
	}

	/**
	 * Shelby Levinson should be in the collection, because she submitted only
	 * one grade
	 */
	@Test
	public void testShelbyPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.shelby));
	}
	
	/**
	 * Walter Payne should be in the collection, because his current batch has no assessments
	 */
	@Test
	public void testWalterPresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.walter));
	}
	
	/**
	 * Natalie Church should be in the collection, because her past batch has no assessments
	 */
	@Test
	public void testNataliePresent() {
		assertTrue(this.trainersToSubmitGrades.contains(this.natalie));
	}
	
	/**
	 * Archer Radcliff should be absent from the collection, because even 
	 * though he is missing a grade, the trainee associated with 
	 * that grade was dropped and should be ignored.
	 */
	@Test
	public void testArcherAbsent() {
		assertFalse(this.trainersToSubmitGrades.contains(this.archer));
	}

}
