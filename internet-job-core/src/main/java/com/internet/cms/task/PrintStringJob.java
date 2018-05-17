package com.internet.cms.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class PrintStringJob implements org.quartz.Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("PrintStringJob.execute() " + "ypxtess");
	}

	
}
