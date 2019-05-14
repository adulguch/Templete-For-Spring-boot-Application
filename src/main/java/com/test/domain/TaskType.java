package com.test.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TaskType {

	public TaskType() {		
	}
	
	@Getter
	@Setter
	public int  TaskLevel;
	
	@Getter
	@Setter	
	public String  TaskType;
	
	@Getter
	@Setter	
	public int  TaskIndex;
	
	
	@Builder
	public TaskType(int taskLevel , String taskType, int taskIndex) {	
		this.TaskLevel = taskLevel ;
		this.TaskType =  taskType ;  	
		this.TaskIndex = taskIndex;
	}
	
}
