package com.test.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TaskProperty {

	public TaskProperty() {		
	}
	
	@Getter
	@Setter
	public List<TaskType> taskTypeList;
	
	@Getter
	@Setter	
	public List<String> taskStatusList;
	
	@Getter
	@Setter	
	public List<String> taskPriorityList;
	
	
	@Getter
	@Setter	
	public int taskMaxLevel;
	
	@Builder	
	public TaskProperty(List<TaskType> taskTypeList , List<String>  taskStatusList, List<String>  taskPriorityList,  int taskMaxLevel) {	
		this.taskStatusList = taskStatusList;	
		this.taskTypeList = taskTypeList;	
		this.taskPriorityList =  taskPriorityList; 
		this.taskMaxLevel = taskMaxLevel; 
		
	}
	
}
