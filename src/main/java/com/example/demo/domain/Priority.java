package com.example.demo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Priority {
	
	 	@Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long priorityId;
		private String name;
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "priority")
		private List<Task> tasks;
		
		public Priority() {}
		
		public Priority(String name) {
			super();
			this.name = name;
		}
		
		public Long getPriorityId() {
			return priorityId;
		}
		
		public void setPriorityId(Long priorityId) {
			this.priorityId = priorityId;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}

		public List<Task> getTasks() {
			return tasks;
		}

		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}

		@Override
		public String toString() {
			return "Priority [priorityId=" + priorityId + ", name=" + name + "]";
		}
}

