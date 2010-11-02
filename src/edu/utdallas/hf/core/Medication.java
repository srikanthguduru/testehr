package edu.utdallas.hf.core;
/** 
 * As part of the final documentation, Code Comments will be included, as specified by Razo
 * 
 * */
public class Medication {
	private int id;
	private int pid;
	private String drug;
	private int dosage;
	private int unit;
	private boolean active;
	


	public Medication(){
		id = 0;
		pid = 0;
		drug = "";
		dosage = 0;
		unit = 0;
		active = false; 
	}
	
	public Medication(int i, int p, String d, int dos, int u, boolean a){
		id = i;
		pid = p;
		drug = d;
		dosage = dos;
		unit = u;
		active = a;	
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public int getDosage() {
		return dosage;
	}
	public void setDosage(int dosage) {
		this.dosage = dosage;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	
	public String getDrugString(){
		return drug+"/"+dosage+"/"+unit;
	}
	
	public String getActiveString(){
		if(isActive()){
			return "Active";
		}else
			return "Inactive";
	}
	
	
	
}
