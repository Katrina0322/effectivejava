package com.hust.chainOfResponsibility;

public class Director extends Leader {

	public Director(String name) {
		super(name);
	}

	@Override
	public void handlerRequest(LeaveRequest leaveRequest) {
		if(leaveRequest.getLeaveDays()<3){
			System.out.println("员工"+leaveRequest.getEmpName()+"请假3天");
		}else{
			if(this.nextLeader!=null){
				this.nextLeader.handlerRequest(leaveRequest);
			}
		}
	}

}
