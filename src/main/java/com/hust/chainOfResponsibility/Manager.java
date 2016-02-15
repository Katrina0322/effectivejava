package com.hust.chainOfResponsibility;

public class Manager extends Leader {

	public Manager(String name) {
		super(name);
	}

	@Override
	public void handlerRequest(LeaveRequest leaveRequest) {
		if(leaveRequest.getLeaveDays()<10){
			System.out.println("");
		}else{
			if(this.nextLeader!=null){
				this.nextLeader.handlerRequest(leaveRequest);
			}
		}
	}

}
