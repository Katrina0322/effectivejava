package com.hust.chainOfResponsibility;

public class GeneralManager extends Leader {

	public GeneralManager(String name) {
		super(name);
	}

	@Override
	public void handlerRequest(LeaveRequest leaveRequest) {
		if(leaveRequest.getLeaveDays()<10){
			System.out.println("批准"+leaveRequest.getEmpName()+"的请假");
		}else{
			System.out.println("莫非"+leaveRequest.getEmpName()+"不想干了？");
		}
	}

}
