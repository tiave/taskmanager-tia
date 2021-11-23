package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
	public class Task {
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
	    private String description;
	    private String place;
	    private String date;

	    @ManyToOne
	    @JoinColumn(name = "priorityId")
	    @JsonManagedReference
	    private Priority priority;
	    
	    public Task() {}
	    
	    public Task(String description, String place, String date, Priority priority) {
	    	super();
	    	this.description = description;
	    	this.place = place;
	    	this.date = date;
	    	this.priority = priority;
	    }
	    
	    public Long getId() {
	    	return id;
	    }
	    
	    public void setId(Long id) {
	    	this.id = id;
	    }
	    
	    public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public void setPlace(String place) {
			this.place = place;
		}
		
		public String getPlace() {
			return place;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public Priority getPriority() {
			return priority;
		}

		public void setPriority(Priority priority) {
			this.priority = priority;
		}
		
		@Override
		public String toString() {
			
			if (this.priority != null) {
				return "Task [id=" + id + ", description=" + description + ", place=" + place + "date=" + date + ", priority=" + priority + "]";
		}
			else {
				return "Task [id=" + id + ", description=" + description + ", place=" + place + "date=" + date + "]";
			}
		
		}
}


