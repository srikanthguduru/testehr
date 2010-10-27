package edu.utdallas.hf.core;

public class Vitals {
	private int patientID;
	private int vitalsID;
	private float temperature = 0.0f;
	private float bmi = 0.0f;
	private float height = 0.0f;
	private float weight = 0.0f;
	
	public Vitals(int id, int pid, float h, float w, float t)
	{
		patientID = pid;
		vitalsID = id;
		temperature = t;
		weight = w;
		height = h;
	}
	
	public Vitals(int id, int pid, float Bmi)
	{
		patientID = pid;
		vitalsID = id;
		this.bmi = Bmi;
	}
	
	public Vitals()
	{
		
	}
	
	
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setBmi(float bmi) {
		this.bmi = bmi;
	}
	public float getBmi() {
		return bmi;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setVitalsID(int vitalsID) {
		this.vitalsID = vitalsID;
	}
	public int getVitalsID() {
		return vitalsID;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getHeight() {
		return height;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getWeight() {
		return weight;
	}
	
	
}
